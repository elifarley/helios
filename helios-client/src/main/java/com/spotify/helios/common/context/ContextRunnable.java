/*
 * Copyright (c) 2014 Spotify AB.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.spotify.helios.common.context;

/**
 * A Runnable that will include additional stack trace information if run() throws an
 * Exception.
 */
final class ContextRunnable implements Runnable {
  private final Runnable command;
  private final StackTraceElement[] trace;

  ContextRunnable(final Runnable command) {
    this.command = command;
    this.trace = Context.getStackContext();
  }

  @Override
  public void run() {
    try {
      command.run();
    } catch (final Throwable th) {
      Context.handleException(trace, th);
      throw th;
    }
  }
}
