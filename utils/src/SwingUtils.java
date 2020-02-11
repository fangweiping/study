import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

public class SwingUtils {

    private GridBagConstraints constraints ;

    /**
     * 设置布局约束
     */
    public void setConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        constraints.gridx = gridx;// x轴 第N个单元格
        constraints.gridy = gridy;// y轴 第N个单元格
        constraints.gridwidth = gridwidth;// x轴 占据N个单元格
        constraints.gridheight = gridheight;// y轴占据N个单元格
        constraints.insets = new Insets(10, 10, 10, 10);
    }

    /**
     * 居中显示
     */
    public static void setCenter(Component component, int windowsWidth, int windowsHeight) {
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 设置窗体位置和大小
        component.setBounds((width - windowsWidth) / 2, (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    }

}
