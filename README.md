# Android Activity with Fragments included via element in layout                 
                                                                                 
Android learning project for navigation between Activites that use Fragments.
Each Fragment is associated with its parent Activity via a `<fragment>`
element in the Activity's layout.

> To fix the preview error, edit `res/values/styles.xml` as follows:
> change: &lt;style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar"&gt;
> to:     &lt;style name="AppTheme" parent="Base.Theme.AppCompat.Light.DarkActionBar"&gt;


## Tip: How to view the logger

Use the following steps to view the error when your Android app crashes.

- Select **Tools**, select **Android**, click **Android Device Monitor**.
- In the **Android Device Monitor**, do the following:
    - Select your device in the **Devices** pane.
    - On the lower half of the screen, click the **LogCat** tab.
    - On the lower right side, click the dropdown list and select **error**.


## How to use a Fragment within an Activity

Fragments are standalone components that are not coupled with Activities. Therefore, we need to
associated the Fragment with an Activity and we need to setup communication between the Activity and
its child Fragment.

The high-level process is:

1. Create a Fragment and a layout.
2. Associate the layout with the Fragment in the Fragment's `onCreateView()` callback method.
3. Create an Activity and a layout.
4. Associate the Activity with its layout in the Activity's `onCreate()` callback method.
5. Associate the Fragment with an Activity either:
    a. in the Activity's layout via a `<fragment>` element; or
    b. in the Activity's class via the `FragmentManager` and a `FragmentTransaction`.
6. Establish communication between the Activity and Fragment using any of the following methods:
    a. In the Activity, pass arguments to the Fragment's `newInstance` factory method when the
       Fragment is created.
    b. In the Activity, implement the callback methods defined by the Interface defined in the
       Fragment. These callback methods allow the Fragment to send data to the Activity.
    c. In the Fragment, implement public methods. The Fragment's public methods can be called by
       the Activity to pass data into the Fragment.

Taking a step back, there are 4 points at which we connect the Activity and Fragment:

1. In Activity's layout via `<fragment>` or via Activity's class via `FragmentManager` and
   `FragmentTransaction`.
2. In the Fragment's `newInstance` parameters
3. In the Activity's implementation of an Interface and the Fragment's instance of the Interface
4. In the Fragment's public methods

The 4 steps are explained in more detail below.

1. The Fragment is associated with the Activity via the Activity's layout or in the Activity's
   class. This step inflates the Fragment's layout and inserts it into the Activity's layout.
2. In the Activity, we can pass data into the Fragment via the Fragment's `newInstance` factory
   method. This allows the Activity to pass data into the Fragment when it is instantiated.
3. The Fragment defines public methods that the Activity can call to communicate from the Activity
   to the Fragment.
4. The Activity implements an interface that is defined in the Fragment. This allows the Fragment to
   communicate from the Fragment to the Activity.


### Summary of communication flow between Activity and Fragment

From        | To        | Implementation                         | Use
------------|-----------|----------------------------------------|----------------------------------
Activity    | Fragment  | Fragment's factory method              | During instantiation of Fragment
Activity    | Fragment  | Fragment's public methods              | Pass down to Fragment
Fragment    | Activity  | Activity's implementation of Interface | Pass data up to Activity


### To pass data to the Activity based on an event in the Fragment

1. Bind event handler to view in Fragment's `onCreateView`.
2. On event, event handler is called.
3. In event handler, call an interface method defined in the Activity (via the private member that
   is an Interface instance, ex. `callbacks.someInterfaceMethodDefinedInActivity()`).



## How to recreate this project

### Create project

- Create a new project with a Blank Activity named `AActivity`


### Create ZFragment

A Fragment is a self contained portion of the UI including a layout and logic.

- Create a new Fragment named `ZFragment`.
- Edit `fragment_z.xml` as follow:
    - Change `FrameLayout` to `LinearLayout`.
    - Delete the default `TextView`.
    - Add an `EditText` and `Button` to collect the user's first name.
    - Notice how there is no coupling between the `fragment_z.xml` layout and the `ZFragment`
      Fragment. However, there is a design-time attribute of `tools:context` that is used by Android
      Studio to apply the correct theme when previewing the layout.
    - Next, insert two `LinearLayout` ViewGroups that contains two `TextView` views each. One of
      these `TextView` views will be used to show the value passed from the Activity to the Fragment
      when the Fragment is initialized, and the other displays the value of a variable passed at
      runtime.
- Edit `ZFragment` as shown in the source file.


### Update activity_a.xml

- Edit `fragment_a.xml` as follow:
    - Change `FrameLayout` to `LinearLayout`.
    - Delete the default `TextView`.
    - Add an `EditText` and `Button` to collect the user's age.
    - Insert two `LinearLayout` ViewGroups that contains two `TextView` views each. One of
      these `TextView` views will be used to show the value passed from YFragment to AActivity,
      and the other will show the value passed from ZFragment to AActivity.

### Update AActivity

- Edit `AActivity` as shown in the source file.

## Notes

The Fragment's factory method is never called in this example we do not manually create an instance
of the Fragment in the activity. Instead, we use the `<fragment>` element and delegate the creation
of an instance of the Fragment to Android.
