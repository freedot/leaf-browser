package fd.browser.leaf;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

class AnimUtil {
    public static void startValAnim(float from, float to, ValueAnimator.AnimatorUpdateListener listener,
                                    long duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(from, to);
        animator.setDuration(duration);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(listener);
        animator.start();
    }

    public static <V extends View> void startZoomAnim(V v, float from, float to) {
        startValAnim(from, to, animation -> {
            float curScale = Float.valueOf(animation.getAnimatedValue().toString());
            v.setScaleX(curScale);
            v.setScaleY(curScale);
        }, 360);
    }
}

public class MainActivity extends AppCompatActivity {
    private boolean mOpenedPagesExpanded = false;
    private TilesLayout mOpenedPagesBar;
    private ImageView mImage;
    private String mPageImages[] = new String[] {
            "http://img.hb.aicdn.com/10dd7b6eb9ca02a55e915a068924058e72f7b3353a40d-ZkO3ko_fw658",
            "http://img.hb.aicdn.com/a3a995b26bd7d58ccc164eafc6ab902601984728a3101-S2H0lQ_fw658",
            "http://pic4.nipic.com/20091124/3789537_153149003980_2.jpg",
            "http://img.hb.aicdn.com/4ba573e93c6fe178db6730ba05f0176466056dbe14905-ly0Z43_fw658",
            "http://img.hb.aicdn.com/4bc60d00aa3184f1f98e418df6fb6abc447dc814226ef-ZtS8hB_fw658",
            "http://img.hb.aicdn.com/d9a48c272914c5253eceac26c51a56a26f4e50d048ba7-IJsbou_fw658",
            "http://img.hb.aicdn.com/03d474bbe20efb7df9aed4541ace70b53b53c70bdfe3-8djYVv_fw658",
            "http://img.hb.aicdn.com/004cddd40519846281526b4b25fbdea36b31d01e190dd-7zlmuG_fw658",
            "http://img.hb.aicdn.com/a58eda8a9a2a3f30f0a694c2702e1aba71d97d616d34f-rqv6FA_fw658",
            "http://img.hb.aicdn.com/41ff5110b4ecdec24e14f767e83c1659c2e8a180f3df-QqUAgk_fw658",
            "http://img.hb.aicdn.com/80006ed344ed8dee7ad8142b3c4dc1b51cbf207c3097a-SGiu5P_fw658",
            "http://img.hb.aicdn.com/ade29e312dc44eb63aa6fd258cabcb4a217d88fb4959e-9qPoRi_fw658",
            "http://img.hb.aicdn.com/d812c602d55419c278d826591d4996e744c629d6fb61-01BfFo_fw658",
            "http://img.hb.aicdn.com/1ce7724403f4613566d9d51ad5ead3a58f93220521c3f-fNA43p_fw658",
            "http://img.hb.aicdn.com/e7d7f513744141140f0a34eb3f24540c96ea2b6050ad8-E44srp_fw658",
            "http://img.hb.aicdn.com/04beba3c6f48abc7d8cea6304f8673cfc3de8637322d2-WDz41G_fw658"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.id_content);

        mOpenedPagesBar = (TilesLayout) findViewById(R.id.opened_pages);
        mOpenedPagesBar.setAdapter(new TilesLayout.Adapter() {
            @Override
            public int getLayoutId() {
                return R.layout.item_layout;
            }

            @Override
            public void bindView(View view, int position) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (viewHolder == null) {
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView) view.findViewById(R.id.imageView);
                    view.setTag(viewHolder);
                }
                Glide.with(MainActivity.this).load(mPageImages[position]).into(viewHolder.imageView);
            }

            @Override
            public int getItemCount() {
                return mPageImages.length;
            }

            @Override
            public void displaying(int position) {
            }

            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
            }

            @Override
            public void touchRelease() {
                if (!mOpenedPagesExpanded) {
                    AnimUtil.startZoomAnim(mOpenedPagesBar, 0.5f, 1.0f);
                    mOpenedPagesExpanded = true;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFullScreen();
    }

    private void setFullScreen() {
        View fullscreen = findViewById(R.id.fullscreen_content);
        fullscreen.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    class ViewHolder {
        ImageView imageView;
    }
}