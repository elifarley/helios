package com.spotify.helios.cli.command;

import com.spotify.helios.common.Client;

import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MasterListCommand extends ControlCommand {

  public MasterListCommand(Subparser parser) {
    super(parser);
  }

  @Override
  int run(final Namespace options, final Client client, final PrintStream out)
      throws ExecutionException, InterruptedException {
    final List<String> agents = client.listMasters().get();
    for (final String agent : agents) {
      out.println(agent);
    }
    return 0;
  }
}