package com.example.tuitionclasses.user.intro

class User {
    var name:String?= null
    var email:String?= null
    var uid:String?= null
    var img:Int?= null
    constructor(){}

    constructor(name:String?,email:String?,uid:String?,img:Int?){
        this.name=name
        this.email=email
        this.uid=uid
        this.img=img
    }
}