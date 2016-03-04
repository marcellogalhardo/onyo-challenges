package br.com.mgalhardo.onyo.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.mgalhardo.onyo.R;
import br.com.mgalhardo.onyo.model.implementation.Category;
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.model.implementation.Image;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryListAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category != null) {
            if (!category.image.isEmpty()) {
                Image image = category.image.get(0);
                Picasso.with(context)
                        .load(image.url)
                        .into(holder.image);
            }
            holder.title.setText(category.name);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView image;

        @Bind(R.id.title)
        TextView title;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

    }

}
