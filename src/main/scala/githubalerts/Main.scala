package githubalerts

import cats.effect.{ExitCode, IO, IOApp}

object Main extends IOApp {
  def run(args: List[String]) =
    GithubalertsscalarafapServer.stream[IO].compile.drain.as(ExitCode.Success)
}
