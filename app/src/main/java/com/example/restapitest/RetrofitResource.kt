package com.example.restapitest

data class GetTestResponse(
        var code: Int? = null,
        var message: String? = null,
        var data: Any? = null
//        var data: ArrayList<GetTestData>? = null
)

data class GetTestData(
        var checkIdYn: String? = null
)

data class PostSignupRequest(
        var userNm: String? = null,
        var userId: String? = null,
        var userPw: String? = null,
        var phoneNo: String? = null
)

data class PostSignupResponse(
        var code: Int? = null,
        var message: String? = null,
        var data: String? = null
)