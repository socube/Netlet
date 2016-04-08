/*
 * Copyright (c) 2013 DataTorrent, Inc. ALL Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datatorrent.netlet;

import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datatorrent.netlet.Listener.ServerListener;

/**
 * <p>Abstract AbstractServer class.</p> *
 *
 * @since 1.0.0
 */
public abstract class AbstractServer implements ServerListener
{
  SocketAddress boundAddress;

  @Override
  public void registered(SelectionKey key)
  {
    boundAddress = ((ServerSocketChannel)key.channel()).socket().getLocalSocketAddress();
  }

  @Override
  public void unregistered(SelectionKey key)
  {
  }

  @Override
  public void handleException(Exception cce, EventLoop el)
  {
    logger.error("Exception in event loop {}", el, cce);
  }

  public SocketAddress getServerAddress()
  {
    return boundAddress;
  }

  private static final Logger logger = LoggerFactory.getLogger(AbstractServer.class);
}
