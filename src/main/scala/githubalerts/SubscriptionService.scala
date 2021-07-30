package githubalerts

import cats.Applicative
import cats.implicits._

class SubscriptionService[F[_]: Applicative](hello: HelloWorld[F], jokes: Jokes[F]) {

  def getSubscriptions(user: String): F[List[Subscription]] =
    List(Subscription("47deg", user, "2021-04-15T12:30:15")).pure[F]

  def getJoke: F[Jokes.Joke] = jokes.get

  def hello(n: HelloWorld.Name): F[HelloWorld.Greeting] = hello.hello(n)

}
