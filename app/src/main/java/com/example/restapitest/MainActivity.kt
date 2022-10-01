package com.example.restapitest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restapitest.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!! // 매번 null 체크를 할 필요 없이 편의성을 위해 바인딩 변수 재 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = RetrofitClient.getInstance()
        val service = retrofit.create(RetrofitService::class.java)

        binding.btnGet.setOnClickListener{
            if(binding.edtUserIdGet.length() < 1) {
                Toast.makeText(this, "정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                service.getTestGet2(binding.edtUserIdGet.text.toString()).enqueue(object : Callback<GetTestResponse> {
                    override fun onResponse(call: Call<GetTestResponse>, response: Response<GetTestResponse>) {
                        if(response.isSuccessful){
                            // 정상적으로 통신이 성공된 경우
                            var resource: GetTestResponse? = response.body()
                            var code: Int? = resource?.code
                            var message: String? = resource?.message
                            var dataArrayList: Any? = resource?.data
                            Log.d("rest통신", "onResponse 성공: " + resource?.toString())
                            binding.txtGetResult.setText("Result : " + code + ", " + message + ", " + dataArrayList)
                        }else{
                            // 통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("rest통신", "onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<GetTestResponse>, t: Throwable) {
                        // 통신 실패 (인터넷 끊킴, 예외 발생 등 시스템적인 이유)
                        Log.d("rest통신", "onFailure 에러: " + t.message.toString());
                    }
                })
            }
        }

        binding.btnPost.setOnClickListener{
            if(binding.edtUserNmPost.length() < 1 || binding.edtUserIdPost.length() < 1
                || binding.edtUserPwPost.length() < 1 || binding.edtPhoneNoPost.length() < 1) {
                Toast.makeText(this, "정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val data = PostSignupRequest(binding.edtUserNmPost.text.toString(), binding.edtUserIdPost.text.toString(),
                    binding.edtUserPwPost.text.toString(), binding.edtPhoneNoPost.text.toString())
                service.getSignupPost(data).enqueue(object : Callback<PostSignupResponse> {
                    override fun onResponse(call: Call<PostSignupResponse>, response: Response<PostSignupResponse>) {
                        if(response.isSuccessful) {
                            //정상적으로 통신이 성고된 경우
                            var resource: PostSignupResponse? = response.body()
                            var code: Int? = resource?.code
                            var message: String? = resource?.message
                            var data: String? = resource?.data
                            Log.d("rest통신", "onResponse 성공: " + resource?.toString())
                            binding.txtPostResult.setText("Result :" + code + ", " + message + ", " + data)
                        } else {
                            //통신이 실패한 경우(응답코드 3xx, 4xx 등)
                            Log.d("rest통신", "onResponse 실패")
                        }
                    }
                    override fun onFailure(call: Call<PostSignupResponse>, t: Throwable) {
                        //통신 실패(인터넷 끊김, 예외 발생 등 시스템적인 이유)
                        Log.d("rest통신", "onFailure 에러 : " + t.message.toString())
                    }
                })
            }
        }
    }
}