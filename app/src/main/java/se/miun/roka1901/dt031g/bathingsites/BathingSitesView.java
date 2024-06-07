package se.miun.roka1901.dt031g.bathingsites;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import se.miun.roka1901.dt031g.bathingsites.databinding.BathingSitesViewBinding;

public class BathingSitesView extends ConstraintLayout {
    ImageView imageView;
    TextView badplatView;
    int antal_badplatser = 0;

    public BathingSitesView(@NonNull Context context) {
        super(context);
        initialize(context, null);
    }

    public BathingSitesView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context, attrs);
    }

    public BathingSitesView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs);
    }


    public void setAntal_badplatser(int antal_badplatser) {
        this.antal_badplatser += antal_badplatser;
    }

    public String getBadplatView() {
        return String.format(getContext().getString(R.string.antal_badplatser), antal_badplatser);
    }

    public void initialize(Context context, AttributeSet attrs) {
        BathingSitesViewBinding binding = BathingSitesViewBinding.inflate(LayoutInflater.from(context), this, true);
        imageView = binding.badplatsSymbol2;
        badplatView = binding.badplatserTextVeiw2;
        badplatView.setText(getBadplatView());
        imageView = findViewById(R.id.badplats_symbol2);
        imageView.setOnClickListener(v -> {
            setAntal_badplatser(1);
            badplatView.setText(getBadplatView());
        });
    }


}