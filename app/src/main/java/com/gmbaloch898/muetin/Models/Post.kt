package com.gmbaloch898.muetin.Models

class Post {
    var postUrl: String =""
    var caption: String =""

    constructor()

    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }

}