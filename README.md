# VerificationCode
[![](https://jitpack.io/v/AbuZaitoun/VerificationCode.svg)](https://jitpack.io/#AbuZaitoun/VerificationCode)

A layout for verification and confirmation codes.  
It uses native view elements.. and some magic :mage:

## Gradle
In build.gradle:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
Then add module to dependencies:
```
dependencies {  
  ...
  implementation 'com.github.AbuZaitoun:VerificationCode:Tag'  
}
```
## Usage
In XML:
```
    <com.abuzaitoun.verificationcode.DigitLayout
        android:id="@+id/six_digit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"/>
```
Java:  
`
DigitLayout sixDigitLayout = findViewById(R.id.six_digit);
`
* Get code from layout:
```
String code = sixDigitLayout.getCode();
```
* Enable clipboard support: 
```
sixDigitLayout.setActivity(getActivity());
ClipboardManager clipboardManager = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
sixDigitLayout.setClipboardManager(clipboardManager);     
```
* More features and customizations will be added soon.


