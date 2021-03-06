package no.uib.info331.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import no.uib.info331.R;
import no.uib.info331.models.User;

/**
 * Custom adapter for populating ListViews with users.
 *
 * @author Edvard Pires Bjørgen
 *
 **/

public class UserListViewAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final int textViewResourceId;
    private final List<User> users;
    private LayoutInflater inflater;

    public UserListViewAdapter(Context context, int textViewResourceId, List<User> users) {
        super(context, textViewResourceId, users);
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.textViewResourceId = textViewResourceId;
        this.users = users;
    }

    public int getCount() {
        return users.size();
    }
    public User getItem(int position) {
        return users.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            holder = new UserHolder();
            row = inflater.inflate(textViewResourceId, parent, false);
            holder.circleImageViewUserImage = (CircleImageView) row.findViewById(R.id.circleimageview_user_image);
            holder.textViewUserName = (TextView) row.findViewById(R.id.textview_username_list);

            row.setTag(holder);

        } else {
            holder = (UserHolder) row.getTag();
        }

        holder.textViewUserName.setText(users.get(position).getUsername());
        Drawable imageResource = ContextCompat.getDrawable(context, R.drawable.avatar2);
        String url = "url";
        Picasso.with(context)
                .load(url)
                .centerCrop()
                .fit()
                .placeholder(imageResource)
                .into(holder.circleImageViewUserImage);
        return row;
    }

    static class UserHolder {
        CircleImageView circleImageViewUserImage;
        TextView textViewUserName;
    }
}
