package cafemanagement;
import java.awt.Color;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;

/**
 * A simple demonstration application showing how to create a Gantt chart with
 * multiple bars per task.
 */
public class GanttDemo2 extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */ 
    public int id;
    public GanttDemo2(String title,int id) throws SQLException {
        super(title);
        this.id = id;
        JPanel chartPanel = createDemoPanel();

        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
        //URLConnection.setContentHandlerFactory(fac)ntPane(chartPanel);
    }
    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private static JFreeChart createChart(IntervalCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createGanttChart(
            "Dự án website",  // chart title
            "Task",              // domain axis label
            "Date",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true);
        plot.getDomainAxis().setMaximumCategoryLabelWidthRatio(10.0f);
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        return chart;
    }

    /**
     * Creates a sample dataset for a Gantt chart, using sub-tasks.  In
     * general, you won't hard-code the dataset in this way - it's done here so
     * that the demo is self-contained.
     *
     * @return The dataset.
     */
    private  IntervalCategoryDataset createDataset() throws SQLException {
//        int id = GanttDemo2.this.id;
        TaskSeries s1 = new TaskSeries("Thời gian");
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT `Tencongviec`,`Ngaybatdau`,`Ngayketthuc`,`Trangthai` FROM `congviec` WHERE `IDduan`=?");
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                String tencv = rs.getString("Tencongviec");
                String ngaybd = rs.getString("Ngaybatdau");
                String ngaykt = rs.getString("Ngayketthuc");
                String trangthai = rs.getString("Trangthai");
                float tt=Float.parseFloat(trangthai);
                System.out.println(tencv);
               Task t1 = new Task(tencv,
                date(ngaybd), date(ngaykt));
                t1.setPercentComplete(tt);
                s1.add(t1);
            }
        } catch (Exception e) {

        }
        
        
        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);

        return collection;
    }

    /**
     * Utility method for creating <code>Date</code> objects.
     *
     * @param day  the date.
     * @param month  the month.
     * @param year  the year.
     *
     * @return A date.
     */
    private static Date date(String date1) {
        String year1 = date1.substring(0, 4);
        String month1 = date1.substring(5, 7);
        String day1 = date1.substring(8, 10);
        System.out.println(year1+" "+month1+" "+day1);
        int year=Integer.parseInt(year1);
        int month=Integer.parseInt(month1);
        int day=Integer.parseInt(day1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date result = calendar.getTime();
        return result;
    }

    /**
     * Creates a panel for the demo (used by SuperDemo.java).
     *
     * @return A panel.
     */
    public JPanel createDemoPanel() throws SQLException {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        return panel;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
 

}