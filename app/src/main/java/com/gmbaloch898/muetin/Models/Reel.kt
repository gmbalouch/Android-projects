package com.gmbaloch898.muetin.Models

class Reel {
    var reelUrl: String =""
    var caption: String =""
    var profileLink:String?=null

    constructor()

    constructor(reelUrl: String, caption: String) {
        this.reelUrl = reelUrl
        this.caption = caption
    }

    constructor(reelUrl: String, caption: String, profileLink: String) {
        this.reelUrl = reelUrl
        this.caption = caption
        this.profileLink = profileLink
    }

}