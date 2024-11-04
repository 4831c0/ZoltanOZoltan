package c03148.zoltan.data

val stubZoltan = Zoltan("Spározoltóán", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.1oxwSGDU-PXNDKiLFbIZRwHaD8%26pid%3DApi&f=1&ipt=a34743e8b4d91bbe45dcf0eab7a68561fa183d889dab4e0e0778bd89cf71f488&ipo=images")
val stubZoltanList = arrayListOf(
    stubZoltan,
    Zoltan("Interspározoltán", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.PyAIg_AFjrUJlpqpI2dgLgHaDy%26pid%3DApi&f=1&ipt=f8912eb8c0b710bc0abda0ef6d8a71357e720aa6388b98cfa18bc4443a7654a6&ipo=images"),
    Zoltan("Iskolázoltán", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse3.mm.bing.net%2Fth%3Fid%3DOIP.mW5CCYMZMjDdYSfdqDaSTAHaE8%26pid%3DApi&f=1&ipt=acd2d9344e2ced3d3d200c7a7222b4473ddb3b304414b95886727e35ccb6ffef&ipo=images"),
    Zoltan("Tescozoltán", "https://external-content.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.canor.hu%2Fuploads%2Fkepek%2F1998_tesco%2Ftesco-dunaujvaros-500-01.jpg&f=1&nofb=1&ipt=f18779108a2721f0534d8c5aca997866358fc7db358a2dac7136ce18068e8315&ipo=images"),
    Zoltan("Kukázoltán", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fd1mkxvwtzddm9n.cloudfront.net%2Froot%2Fproduction%2Freport%2F48%2F48931%2F223864-5d2c46f5db8a1.jpg%3F1632691250&f=1&nofb=1&ipt=e2bbc23ac18533b30bbef0823ec925917cd3a97fd7222fe2bb813d62dd031edb&ipo=images")
)
val stubFriends = arrayListOf<User>(
    User(1, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, mutableListOf(), 1),
    User(2, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, mutableListOf(), 2),
    User(3, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, mutableListOf(), 3),
    User(4, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, mutableListOf(), 4),
    User(5, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, mutableListOf(), 5),
)
val stubUser = User(0, "https://thispersondoesnotexist.com", "Zoltánozó Zoltán", stubZoltanList, stubFriends, 1)