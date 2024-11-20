package c03148.zoltan.data

data class User(val userID: Int, val picture: String,
                var username: String, var zoltan: MutableList<Zoltan>,
                var friends: MutableList<User>, var level: Long)