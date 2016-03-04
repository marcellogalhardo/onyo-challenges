package br.com.mgalhardo.onyo.ui.companylist;

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
import br.com.mgalhardo.onyo.model.implementation.Company;
import br.com.mgalhardo.onyo.model.implementation.Image;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder> {

    private Context context;
    private List<Company> companies;
    private OnItemClickListener onItemClickListener;

    public CompanyListAdapter(Context context, List<Company> companies) {
        this.context = context;
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Company company = companies.get(position);
        if (company != null) {
            if (!company.image.isEmpty()) {
                Image image = company.image.get(0);
                Picasso.with(context)
                        .load(image.url)
                        .fit()
                        .centerCrop()
                        .into(holder.image);
            }
            holder.title.setText(company.displayName);
            holder.subtitle.setText(company.address);
        }
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image)
        ImageView image;

        @Bind(R.id.title)
        TextView title;

        @Bind(R.id.subtitle)
        TextView subtitle;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Company company = companies.get(position);
                    onItemClickListener.onItemClickListener(company);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClickListener(Company company);
    }

}
