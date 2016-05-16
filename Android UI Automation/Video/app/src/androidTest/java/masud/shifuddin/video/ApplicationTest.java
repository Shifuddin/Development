package masud.shifuddin.video;

import android.app.Application;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends InstrumentationTestCase {

    private UiDevice myDevice;
    private UiScrollable listView;
    private UiObject listViewItem;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        myDevice = UiDevice.getInstance(getInstrumentation());
    }

    public void testMenu() throws Exception {

        // Open Apps
        Thread.sleep(2000);
        myDevice.pressHome();
        UiObject2 appsButton = myDevice.findObject(By.desc("Apps"));
        appsButton.click();
        Thread.sleep(5000);

        // Click TUM Campus App
        listView = new UiScrollable(new UiSelector());
        listView.setAsVerticalList();
        listView.setMaxSearchSwipes(10);
        listView.scrollTextIntoView("TUM Campus App");
        listView.waitForExists(5000);
        listViewItem = listView.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), "TUM Campus App");
        listViewItem.clickAndWaitForNewWindow(5000);

        //Goto menu and Show Logged User
        Thread.sleep(3000);
        UiObject obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        listView = new UiScrollable(new UiSelector());
        listView.scrollToBeginning(10, 5);
        Thread.sleep(6000);

        // Come out from Menu
        myDevice.click(myDevice.getDisplayWidth()-50, myDevice.getDisplayHeight()-100);
        Thread.sleep(1000);

        // Show Current News
        listView.scrollToEnd(10, 150);
        Thread.sleep(3000);

        // Show one news for example movie
        Thread.sleep(2000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Codename U.N.C.L.E.");
        listView.waitForExists(3000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.widget.FrameLayout").index(1));
        listViewItem = listView.getChildByText(new UiSelector().className("android.widget.TextView"), "Codename U.N.C.L.E.");
        listViewItem.click();
        Thread.sleep(5000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollToEnd(10, 150);

        //Back to main application
        myDevice.pressBack();
        Thread.sleep(2000);

        //Goto menu
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(4000);

        // Goto My lecture
        //  Thread.sleep(3000);
        listView.scrollTextIntoView("My Lectures");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()),"My Lectures");
        listViewItem.click();

        // Select One Lecture
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Praktikum - Betriebssysteme - Google Android (IN0012, IN2106, IN4004)");
        listView.waitForExists(3000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.view.ViewGroup").index(3));
        listViewItem = listView.getChildByText(new UiSelector()
                .className(android.widget.TextView.class.getName()), "Praktikum - Betriebssysteme - Google Android (IN0012, IN2106, IN4004)");
        listViewItem.click();

        //Lecture Appointments
        Thread.sleep(3000);
        listViewItem = listView.getChildByText(new UiSelector()
                .className(android.widget.Button.class.getName()), "Lecture Appointments");
        listViewItem.click();
        Thread.sleep(2000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollToEnd(10, 150);
        Thread.sleep(1000);
        myDevice.pressBack();
        myDevice.pressBack();
        myDevice.pressBack();

        // Again Menu
        Thread.sleep(3000);
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(6000);

        // Choice MVG
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("MVV");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "MVV");
        listViewItem.click();

        // click search button
        Thread.sleep(2000);
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageView"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(6000);

        // Type Studentenstadt
        obj = myDevice.findObject(new UiSelector().className("android.widget.EditText"));
        obj.setText("Studentenstadt");
        Thread.sleep(2000);
        myDevice.pressEnter();
        Thread.sleep(3000);

        // show all options
        listView = new UiScrollable(new UiSelector());
        listView.scrollToEnd(10, 150);
        Thread.sleep(2000);
        myDevice.pressBack();
        myDevice.pressBack();
        myDevice.pressBack();

        // Goto menu
        Thread.sleep(3000);
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(6000);

        // Open Organization
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Organizations");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Organizations");
        listViewItem.click();

        // Open Informatik
        Thread.sleep(2000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Informatics");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Informatics");
        listViewItem.click();

        // Show Informatik
        Thread.sleep(2000);
        listView.scrollToEnd(10, 150);
        Thread.sleep(1000);
        myDevice.pressBack();

        // Open TUM Board of Management
        Thread.sleep(2000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("TUM Board of Management");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "TUM Board of Management");
        listViewItem.click();

        //show tum board of management
        Thread.sleep(2000);
        listView.scrollToEnd(10, 150);
        Thread.sleep(1000);
        myDevice.pressBack();
        myDevice.pressBack();

        // Goto menu
        Thread.sleep(3000);
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(6000);


        // Open Opening Hours
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Opening Hours");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Opening Hours");
        listViewItem.click();

        // Opne Libraries
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Libraries");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Libraries");
        listViewItem.click();

        // Show libraries
        Thread.sleep(1000);
        listView.scrollToEnd(10, 150);
        Thread.sleep(1000);
        myDevice.pressBack();
        myDevice.pressBack();

        //Goto menu
        Thread.sleep(3000);
        obj = myDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        obj.clickAndWaitForNewWindow(599);
        Thread.sleep(6000);

        // select Plans
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Plans");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Plans");
        listViewItem.click();

        // Open MVV Plan
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("MVV Train-Network");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "MVV Train-Network");
        listViewItem.clickAndWaitForNewWindow(6000);
        Thread.sleep(2000);
        myDevice.pressBack();

        //Opne Garcing Plan
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Campus Garching");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Campus Garching");
        listViewItem.clickAndWaitForNewWindow(6000);
        Thread.sleep(2000);
        myDevice.pressBack();
        myDevice.pressBack();

        // Goto Settings
        listView = new UiScrollable(new UiSelector());
        listViewItem = listView.getChild(new UiSelector().resourceId("de.tum.in.tumcampus:id/action_settings"));
        listViewItem.clickAndWaitForNewWindow(1000);

        // Open Default Campus
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Default campus");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Default campus");
        listViewItem.clickAndWaitForNewWindow(3000);


        // Change Campus
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Main Campus");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Main Campus");
        listViewItem.click();
        Thread.sleep(2000);

        // Change Campus to Garching Again
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Default campus");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "Default campus");
        listViewItem.clickAndWaitForNewWindow(3000);
        Thread.sleep(2000);

        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Campus Garching");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Campus Garching");
        listViewItem.click();
        Thread.sleep(2000);

        // State During Class
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("State during lectures");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()), "State during lectures");
        listViewItem.clickAndWaitForNewWindow(3000);
        Thread.sleep(2000);

        // Change State
        Thread.sleep(1000);
        listView = new UiScrollable(new UiSelector());
        listView.scrollTextIntoView("Silent");
        listView.waitForExists(6000);
        Thread.sleep(3000);
        //listViewItem = listView.getChild(new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").index(3));
        listViewItem = listView.getChildByText(new UiSelector().className(android.widget.CheckedTextView.class.getName()), "Silent");
        listViewItem.click();
        Thread.sleep(2000);

        myDevice.pressBack();



    }
    public void searchTextView_click(String text)
    {

    }
    public void searchCheckTextView_click(String text)
    {

    }
}