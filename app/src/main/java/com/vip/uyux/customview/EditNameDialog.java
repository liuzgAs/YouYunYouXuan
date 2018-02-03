package com.vip.uyux.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vip.uyux.R;
import com.vip.uyux.util.NameLengthFilter;


public class EditNameDialog extends Dialog {

    private Context context;
    private String title;
    private String confirmButtonText;
    private String cacelButtonText;
    private String editStr;
    private ClickListenerInterface clickListenerInterface;
    private EditText editIntro;

    public interface ClickListenerInterface {

        void doConfirm(String intro);

        void doCancel();
    }

    public EditNameDialog(Context context, String title, String editStr, String confirmButtonText, String cacelButtonText) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
        this.editStr = editStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_edit, null);
        setContentView(view);

        TextView tvTitle = (TextView) view.findViewById(R.id.textTitle);
        editIntro = view.findViewById(R.id.editIntro);
        InputFilter[] filters = {new NameLengthFilter(20)};
        editIntro.setFilters(filters);
        TextView tvConfirm = (TextView) view.findViewById(R.id.textQueDing);
        TextView tvCancel = (TextView) view.findViewById(R.id.textQuXiao);
        editIntro.setText(editStr);
        editIntro.setSelection(editStr.length());
        tvTitle.setText(title);
        tvConfirm.setText(confirmButtonText);
        tvCancel.setText(cacelButtonText);

        tvConfirm.setOnClickListener(new clickListener());
        tvCancel.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            if (id==R.id.textQueDing){
                if (TextUtils.isEmpty(editIntro.getText().toString().trim())){
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                clickListenerInterface.doConfirm(editIntro.getText().toString().trim());
            }else if (id==R.id.textQuXiao){
                clickListenerInterface.doCancel();
            }
        }

    };

}