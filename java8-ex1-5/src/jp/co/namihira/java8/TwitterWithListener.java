/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * みなさんのプロジェクトの1つから、ActionListenerやRunnableといったインターフェースを多数使用しているファイルを1つ選んでください。
 * それらのインタフェースの使用をラムダ式で置き換えなさい。
 * 置き換えた結果、コードが何行短くなりましたか。コードは読みやすくなりましたか？
 */
/**
 * A.
 * 2箇所のActionListenerをLambdaに置き換えた。
 * コード削減量：import文 2行削減、クラス宣言　9行削減。
 * 個人的に所感：
 *  この程度なら読みやすさはあまり変わらないと思った。
 *  しかし、コードを書く際には、Listenterクラス（シンタックス）を意識しなくてよく、
 *  メソッドを直に読んでいるのように書けるので、楽だと思う。
 */

package jp.co.namihira.java8;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * This class can not be compiled.
 * - a part of the code for Digital Clook.
 */
public class TwitterWithListener {

    private JDialog dialog;
    private JTextField textField;
    private JList list;
    private JButton button;
    private DefaultListModel model;
    private JScrollPane sp;
    private JButton tweetButton;
    private JTextField tweetText;
    private Twitter twitter;
    private ClockPanel clockPanel;

    public TwitterWithListener(ClockPanel clockPanel) {
        this.clockPanel = clockPanel;
        twitter = new TwitterFactory().getInstance();

        button = new JButton("button");

        /*** use a Listener ***/
        button.addActionListener(new ButtonActionListener());
        /*********************/

        sp = new JScrollPane();
        model = new DefaultListModel();
        list = new JList(model);
        sp.getViewport().setView(list);
        sp.setPreferredSize(new Dimension(800, 200));

        tweetButton = new JButton("button");
        tweetButton.setEnabled(false);

        /*** use a Listner ***/
        tweetButton.addActionListener(new TwitterButtonActionListener());
        /*********************/

        tweetText = new JTextField();

        dialog = new JDialog();
        dialog.setTitle("Twiiter");
        dialog.setResizable(false);
        dialog.setLayout(new GridBagLayout());
        dialog.add(new JTextField(10), getGridBagConstraints(0, 0, 1, 1, GridBagConstraints.BOTH));
        dialog.add(button, getGridBagConstraints(1, 0, 1, 1, GridBagConstraints.BOTH));
        dialog.add(sp, getGridBagConstraints(0, 1, 2, 1, GridBagConstraints.BOTH));
        dialog.add(tweetButton, getGridBagConstraints(0, 2, 1, 1, GridBagConstraints.BOTH));
        dialog.add(tweetText, getGridBagConstraints(1, 2, 1, 1, GridBagConstraints.BOTH));
        dialog.pack();
    }

    private GridBagConstraints getGridBagConstraints(final int x, final int y, final int width, final int height,
            final int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = fill;
        return gbc;
    }

    public void setVisible(boolean flag) {
        model.clear();
        dialog.setVisible(flag);
    }

    public void getTimeLine() {
        tweetText.setEnabled(false);
        tweetButton.setEnabled(false);
        model.clear();
        try {
            List<Status> statuses;
            String accountName = textField.getText();
            if (accountName.equals("")) {
                statuses = twitter.getPublicTimeline();
                for (Status status : statuses) {
                    model.addElement("[" + status.getUser().getName() + "]:" + status.getText());
                }
            } else {
                if (accountName.equals("namihira_k")) {
                    tweetText.setEnabled(true);
                    tweetButton.setEnabled(true);
                }
                statuses = twitter.getUserTimeline(accountName);
                for (Status status : statuses) {
                    model.addElement(":" + status.getText());
                }
            }
        } catch (TwitterException e) {
            model.addElement("HTTP/1.1 " + e.getStatusCode());
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        dialog.pack();
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            getTimeLine();
        }
    }

    private class TwitterButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            updateTwitterStatus();
    }

    private void updateTwitterStatus() {
        String tweet = tweetText.getText();
        try {
            twitter.updateStatus(tweet + " at " + clockPanel.getTime());
            Thread.sleep(1000);
        } catch (TwitterException te) {
            te.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tweetText.setText("");
        getTimeLine();
    }


}
