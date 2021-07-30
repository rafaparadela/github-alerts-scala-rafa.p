package githubalerts

case class Subscription(
  organization: String,
  repository: String,
  subscribedAt: String
)