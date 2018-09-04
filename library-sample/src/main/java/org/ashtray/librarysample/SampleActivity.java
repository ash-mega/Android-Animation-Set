package org.ashtray.librarysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class SampleActivity extends AppCompatActivity {
    
    private Updater updater;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                click();
                new InnerUpdater().refresh();
                updater.refresh();
            }
        };
    }
    
    protected abstract void click();
    
    public interface Updater {
        
        void refresh();
    }
    
    public static class InnerUpdater implements Updater {
        
        @Override
        public void refresh() {
        
        }
    }
}
