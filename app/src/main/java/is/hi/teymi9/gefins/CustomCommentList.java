package is.hi.teymi9.gefins;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.UUID;

import is.hi.teymi9.gefins.model.Comment;
import is.hi.teymi9.gefins.service.AdService;


/**
 * Gerir custom lista fyrir athugasemdir
 *
 * @author Ólöf Fríða
 * @version 1.0
 */

public class CustomCommentList extends ArrayAdapter<String> {

   private String[] username;
   private String[] comment;
   private Activity context;
   private UUID[] commentId;

   public CustomCommentList(Activity context, String[] username, String[] comment, UUID[] commentId) {
       super(context, R.layout.fragment_display_comments,username);

   this.context = context;
   this.username = username;
   this.comment = comment;
   this.commentId = commentId;
   }

   @NonNull
   @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View r = convertView;
       ViewHolder viewHolder = null;

       if(r==null) {
           LayoutInflater layoutInflater = context.getLayoutInflater();
           r=layoutInflater.inflate(R.layout.fragment_comments_layout,null,true);
           viewHolder=new ViewHolder(r);
           r.setTag(viewHolder);
       } else {
           viewHolder = (ViewHolder) r.getTag();
       }
       viewHolder.tvw1.setText(username[position]);
       viewHolder.tvw2.setText(comment[position]);
       if(LoginActivity.getUserService().getCurrentUser().getUsername().compareTo(username[position]) != 0) {

           if(LoginActivity.getUserService().getCurrentUser().getUsername().compareTo(DisplaySingleAdActivity.adService.getCurrentAd().getAdUsername()) !=0){

               viewHolder.button.setEnabled(false);
               viewHolder.button.setVisibility(View.GONE);
           } else {
               viewHolder.button.setEnabled(true);
               viewHolder.button.setVisibility(View.VISIBLE);
               viewHolder.button.setOnClickListener(new View.OnClickListener(){
                   @Override
                   public void onClick(View v) {
                       Comment c = DisplayCommentActivity.commentService.findComment(commentId[position]);
                       DisplayCommentActivity.commentService.deleteComment(c, context);
                   }

               });
           }

       }
       else {
           viewHolder.button.setEnabled(true);
           viewHolder.button.setVisibility(View.VISIBLE);
           viewHolder.button.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View v) {
                   Comment c = DisplayCommentActivity.commentService.findComment(commentId[position]);
                   DisplayCommentActivity.commentService.deleteComment(c, context);
               }

           });
       }
       return r;

   }
    class ViewHolder
    {
       TextView tvw1;
       TextView tvw2;
       Button button;
       ViewHolder(View v)
       {
           tvw1 = v.findViewById(R.id.username);
           tvw2 = v.findViewById(R.id.comment);
           button = v.findViewById(R.id.deleteComment);
       }

    }
}
