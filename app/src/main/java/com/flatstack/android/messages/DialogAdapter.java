package com.flatstack.android.messages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flatstack.android.R;
import com.flatstack.android.messages.models.Dialog;
import com.flatstack.android.utils.Views;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogViewHolder> {

    private final List<Dialog> dialogs;

    DialogAdapter(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }

    @Override
    public DialogAdapter.DialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DialogViewHolder(Views.inflate(R.layout.item_message, parent));
    }

    @Override public void onBindViewHolder(DialogAdapter.DialogViewHolder holder, int position) {
        holder.uiName.setText(dialogs.get(position).name);
        holder.uiMessage.setText(dialogs.get(position).lastMessage);
    }

    @Override public int getItemCount() {
        return dialogs.size();
    }

    class DialogViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.name) TextView uiName;
        @Bind(R.id.message) TextView uiMessage;

        DialogViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
