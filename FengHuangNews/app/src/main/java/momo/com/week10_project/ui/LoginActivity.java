package momo.com.week10_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import momo.com.week10_project.R;
import momo.com.week10_project.fragment.AccountFragment;
import momo.com.week10_project.utils.Constant;
import momo.com.week10_project.utils.LogUtils;


/**
 * 登录界面，mob第三方登录。不懂mob,看mob官方网站学习。
 * 登录返回用户头像，昵称信息。
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView iv_qq = (ImageView) findViewById(R.id.login_qq);
        ImageView iv_weibo = (ImageView)findViewById(R.id.login_sinaweibo);
        ImageView iv_back = (ImageView) findViewById(R.id.activity_login_iv_back);

        iv_qq.setOnClickListener(this);
        iv_weibo.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_login_iv_back:{
                finish();
            }break;
            case R.id.login_qq:{
                loginWeibobyShareSDK(view);
            }break;
            case R.id.login_sinaweibo:{
                loginWeibobyShareSDK(view);
            }break;
        }
    }


    private void loginWeibobyShareSDK(View v) {
        Platform platform=null;
        switch (v.getId()){
            case R.id.login_qq:{
                platform = ShareSDK.getPlatform(QQ.NAME);
            }break;
            case R.id.login_sinaweibo:{
                platform = ShareSDK.getPlatform(SinaWeibo.NAME);
            }break;
        }

        if(platform!=null){
            platform.setPlatformActionListener(new PlatformActionListener() {
                @Override
                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                    //从platform里面获取用户的信息
                    PlatformDb db = platform.getDb();
//                long expiresIn = db.getExpiresIn();
                    String userIcon = db.getUserIcon();
                    String userName = db.getUserName();

                    Intent intent = new Intent(LoginActivity.this,AccountFragment.class);
                    intent.putExtra(Constant.LOGIN_NAME,userName);
                    intent.putExtra(Constant.LOGIN_ICON,userIcon);
                    LoginActivity.this.setResult(2,intent);
                    LoginActivity.this.finish();
                }

                @Override
                public void onError(Platform platform, int i, Throwable throwable) {
                    LogUtils.MyLog("longin onError");
                }

                @Override
                public void onCancel(Platform platform, int i) {
                    LogUtils.MyLog("longin onCancel");
                }
            });

            platform.showUser(null);
        }
    }


}
