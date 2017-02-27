/*
 *
 *    Copyright 2017 Steven Smith, sjs@kana-tutor.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *    either express or implied. See the License for the specific
 *    language governing permissions and limitations under the License.
 */
package com.kana_tutor.clipboardexample;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText sourceTV, destTV;
    Button copyButton, pasteButton;

    private ClipboardManager clipboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sourceTV = (EditText) findViewById(R.id.sourceTV);
        destTV = (EditText) findViewById(R.id.destTV);

        copyButton = (Button) findViewById(R.id.copy_button);
        pasteButton = (Button) findViewById(R.id.paste_button);

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    }

    public void buttonOnClick (View v) {
        int buttonId = v.getId();
        if (buttonId == R.id.copy_button) {
            String text;
            text = sourceTV.getText().toString();

            ClipData cd = ClipData.newPlainText("text", text);
            clipboard.setPrimaryClip(cd);

            Toast.makeText(getApplicationContext()
                , "Copied:\"" + text + "\"",
                Toast.LENGTH_SHORT).show();
        }
        else if (buttonId == R.id.paste_button) {
                ClipData clipData = clipboard.getPrimaryClip();
                ClipData.Item item = clipData.getItemAt(0);

                String text = item.getText().toString();
                destTV.setText(text);

                Toast.makeText(getApplicationContext()
                    , "Pasted:" + text + "\"",
                    Toast.LENGTH_SHORT).show();
            }
        else {
            throw new RuntimeException(
                String.format("Unhandled button. id = 0x%08x", buttonId)
            );
        }
    }

}
