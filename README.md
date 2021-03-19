# VerificationCode
[![](https://jitpack.io/v/AbuZaitoun/VerificationCode.svg)](https://jitpack.io/#AbuZaitoun/VerificationCode)

A layout for verification and confirmation codes.  
It uses native view elements.. and some magic :mage:

![Sample1](https://github.com/AbuZaitoun/VerificationCode/blob/master/images/sample1.png)
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
  implementation 'com.github.AbuZaitoun:VerificationCode:0.2'  
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
* Set number of digits:  
Java:
```
digitLayout.setNumberOfDigits(3);
```
XML: 
```
<com.abuzaitoun.verificationcode.DigitLayout
        ...
        app:digits="4"/>
```
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
Paste call works from any EditText, the copied string will be distributed on all digits if the copied text's length matches the number of digits.
* More features and customizations will be added soon.

## License
```
Copyright 2021 Anas AbuZaitoun

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
