package com.ebookfrenzy.tablayoutdemo;

/**
 * Created by minji on 2017-05-22.
 */

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.viewpager_main);

        Intent intent = new Intent(this.getIntent());
        pager = (ViewPager) findViewById(R.id.pager);

        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 CustomAdapter 객체 생성
        //CustomAdapter에게 LayoutInflater 객체 전달

        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());

        //View Pager에 Adapter설정
        pager.setAdapter(adapter);
    }

    public void mOnClick(View v) {
        int position;

        switch (v.getId()) {
            case R.id.btn_previous:
                position = pager.getCurrentItem();
                //현재우ㅣ치(position)에서 -1해서 이전 position연결
                //첫번쨰ㅏ라미터:설정할 현재위치
                //두번째 파라미터:변경할 떄 부드럽게 이동하는가? false면 팍팍
                pager.setCurrentItem(position - 1, true);
                break;
            case R.id.btn_next:
                position = pager.getCurrentItem(); //현재보여지는 아이템위치 리턴

                //현재위치position에서 +1
                pager.setCurrentItem(position + 1, true);
                break;
        }

    }

}
