package com.example.tuitionclasses.user.intro

class Message {
    var message: String? =null
    var senderTd: String? =null

    constructor(){}

    constructor(message: String?,senderTd:String?){
        this.message=message
        this.senderTd=senderTd
    }
}