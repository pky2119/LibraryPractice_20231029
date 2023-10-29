package kr.ac.wku.librarypractice_20231029

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kr.ac.wku.librarypractice_20231029.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

//        이미지뷰도 클릭 이벤트 지원
//        ViewPhotoActivity로 이동
        binding.imgProfile.setOnClickListener{
            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

        binding.btnCall.setOnClickListener {
//             전화를 바로 걸기 ( 번호만 눌린 화면 X 신호까지 가도록)
//             바로 걸기 (CALL) => 사용자 요금 사용 : 권한 허가 후 사용 가능

            // 권한 획득 성공 여부에 따른 행동 지침을 변수에 저장 (인터페이스 => 객체화 : 익명 클래스 활용)

            val pl = object : PermissionListener{
                override fun onPermissionGranted() {

                    // 권한 허가 되었을 때 할 일 => 전화 걸기

                    val myUri = Uri.parse("tel:010-1111-2222")

                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                    // 거절되면 => 토스트로 안내
                    Toast.makeText(this@MainActivity, "권한 문제로 저노하 연결이 안됩니다", Toast.LENGTH_SHORT).show()
                }

            }
//                  실제로 권한을 물어보자

            TedPermission.create()
                .setPermissionListener(pl)
                .setDeniedMessage("권한 안주면 통화 안됩니다")

            // 화면이 켜지면 바로 이미지 불러내기 ( 웹 상 이미지)

            Glide.with(this).load("https://t1.daumcdn.net/movie/93bdeb491de552e11d7ad8a5dbaecc0ff26086bc").






            }

        }



    }
