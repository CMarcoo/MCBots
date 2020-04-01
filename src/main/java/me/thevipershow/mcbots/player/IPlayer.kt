package me.thevipershow.mcbots.player

interface IPlayer {
    var name : String

    fun place_block() : Boolean
    fun destroy_block() : Boolean
    fun run() : Boolean
    fun crawl() : Boolean
    fun uncrawl() : Boolean
    fun rotate() : Boolean
    fun walk() : Boolean
    fun attack() : Boolean
    fun jump() : Boolean
    fun write_to_chat(message : String) : Boolean
}