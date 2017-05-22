package com.ebookfrenzy.tablayoutdemo;

/**
 * Created by minji on 2017-05-22.
 */


import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomAdapter extends PagerAdapter {

    LayoutInflater inflater;

    public CustomAdapter(LayoutInflater inflater){
        this.inflater=inflater;
    }

    @Override
    public int getCount(){
        return 4;
        //이미지개수리턴
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        View view = null;
        view=inflater.inflate(R.layout.viewpager_childview,null);

        ImageView img = (ImageView)view.findViewById(R.id.img_viewpager_childimage);

        img.setImageResource(R.drawable.image01+position);

        container.addView(view);

        return view;
    }
    // 화면에 보이지 않는 view는 파괴를 해서 메모리를 관리
    // 첫번쨰 파라미터:ViewPager
    // 두번째 파라미터 : 파괴될 View의 인덱스 (가장 처음부터 0,1,2,3...)
    // 세번째 파라미터 : 파괴될 객체(더이상 보이지 않은 View객체)
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){

        //ViewPager에서 보이지않는 View는 제거
        // 세번째 파라미터가 View 객체 이지만 데이터타입이 Object 여서 형변환 실시
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v,Object obj){
        return v==obj;
    }
}
