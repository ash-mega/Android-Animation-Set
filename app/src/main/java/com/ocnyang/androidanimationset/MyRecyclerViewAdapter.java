package com.ocnyang.androidanimationset;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ocnyang.drawableanimation.DrawableAnimationActivity;
import com.ocnyang.propertyanimation.PropertyAnimationActivity;
import com.ocnyang.revealanimation.RevealAnimationActivity;
import com.ocnyang.rippleanimation.TouchFeedbackActivity;
import com.ocnyang.stateanimation.StateAnimationActivity;
import com.ocnyang.transitionanimation.TransitionAnimationActivity;
import com.ocnyang.vectoranimation.VectorAnimationActivity;
import com.ocnyang.viewanimation.ViewAnimationActivity;

/*******************************************************************
 *    * * * *   * * * *   *     *       Created by OCN.Yang
 *    *     *   *         * *   *       Time:2018/3/23 14:06.
 *    *     *   *         *   * *       Email address:ocnyang@gmail.com
 *    * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 *******************************************************************/


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    
    private final int[] mIntArray;
    private final String[] mStringArray;
    private Context mContext;
    
    MyRecyclerViewAdapter(Context context,int[] intArray,String[] stringArray) {
        mContext = context;
        mIntArray = intArray;
        mStringArray = stringArray;
    }
    
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler,parent,false));
    }
    
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,int position) {
        holder.itemNameTv.setText(mStringArray[position]);
        
        Uri uri = Uri.parse("res://" + mContext.getPackageName() + "/" + mIntArray[position]);
        DraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.itemImg.setController(build);
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        to(ViewAnimationActivity.class);
                        break;
                    case 1:
                        to(DrawableAnimationActivity.class);
                        break;
                    case 2:
                        to(PropertyAnimationActivity.class);
                        break;
                    case 3:
                        to(TouchFeedbackActivity.class);
                        break;
                    case 4:
                        to(RevealAnimationActivity.class);
                        break;
                    case 5:
                        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(((MainActivity)mContext),null);
                        Intent intent = new Intent(mContext,TransitionAnimationActivity.class);
                        mContext.startActivity(intent,activityOptionsCompat.toBundle());
                        break;
                    case 6:
                        to(StateAnimationActivity.class);
                        break;
                    case 7:
                        to(VectorAnimationActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    private <T extends Activity> void to(Class<T> target) {
        mContext.startActivity(new Intent(mContext,target));
    }
    
    @Override
    public int getItemCount() {
        return mStringArray != null ? mStringArray.length : 0;
    }
    
    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView itemNameTv;
        SimpleDraweeView itemImg;
        
        ViewHolder(View view) {
            super(view);
            itemView = view.findViewById(R.id.view_content);
            itemImg = view.findViewById(R.id.img_item);
            itemNameTv = view.findViewById(R.id.tv_item);
        }
    }
}
