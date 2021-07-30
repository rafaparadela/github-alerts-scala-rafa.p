package githubalerts

import cats.effect.IO
import org.http4s._
import org.http4s.implicits._
import munit.CatsEffectSuite

class HelloWorldSpec extends CatsEffectSuite {

  test("HelloWorld returns status code 200") {
    assertIO(subscriptionEndpoint.map(_.status) ,Status.Ok)
  }

  test("HelloWorld returns hello world message") {
    assertIO(subscriptionEndpoint.flatMap(_.as[String]), """[{"organization":"47deg","repository":"rafa","subscribedAt":"2021-04-15T12:30:15"}]""".stripMargin)
  }


  private[this] val subscriptionEndpoint: IO[Response[IO]] = {
    val getHW = Request[IO](Method.GET, uri"/subscription/rafa")
    val helloWorld = HelloWorld.impl[IO]
    val jokes = new Jokes[IO] {
      override def get: IO[Jokes.Joke] = IO.delay(Jokes.Joke("My Joke"))
    }
    val service = new SubscriptionService[IO](helloWorld, jokes)
    GithubalertsscalarafapRoutes.appRoutes(service).orNotFound(getHW)
  }
}