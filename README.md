* # Forcard

  > Flow Week1 4분반 팀 김태훈, 박상빈 

  * 포토카드를 생성해주는 Android 기반 어플리케이션입니다.  
  * 포토카드는 연락처와 갤러리 속 사진으로 만들 수 있습니다.  
  * 생성 된 포토카드는 갤러리에 저장, 인스타 스토리 공유가 가능합니다.  
  * 포토카드의 텍스트 크기, 위치, 색상 등을 조정 할 수 있습니다.  

    ![whole](C:\Users\USER\Desktop\whole.png)

  ***

  ### A. 개발 팀원  

  * 한양대 컴퓨터소프트웨어학부 [김태훈](https://github.com/twodf78)  
  * KAIST 전산학부 [박상빈](https://github.com/sbpark0611)  

  ***

  ### B. 개발 환경  

  * OS: Android 

  ```
  minSdk 21
  targetSdk 32
  ```

  * Language: JAVA  

  * IDE: Android Studio  

  * Target Device: Galaxy S10E  

  ***

  ### C. 어플리케이션 소개  

  ### TAB 1 - Phonebook   

  ![whole](https://user-images.githubusercontent.com/48674812/177324044-405e0886-6a53-4444-aa49-dbebaa6c6b16.png)

  #### Major features   

  * Phonebook tab의 우측 하단에 위치한 "+" 버튼을 통해 연락처를 추가할 수 있는 화면으로 이동할 수 있습니다.  
    * 이름과 전화번호, 세부정보를 입력할 수 있습니다. 
  * 특정 연락처를 click 하면 해당 연락처의 이름, 전화번호, 세부정보 및 프로필 사진을 크게 띄워서 볼 수 있습니다.
    * 해당 activity에서 특정 연락처를 삭제하거나 편집할 수 있습니다.
    * 프로필 사진을 클릭하면 큰 화면으로 볼 수 있습니다.
  * 편집화면에서 기존 이름과 연락처와 상세설명, 프로필 사진을 변경 할 수 있습니다
    * 프로필 사진을 클릭하면 갤러리에 있는 다른 사진으로 변경 할 수 있습니다.
  * 특정 연락처를 Long Click 하면 바로 편집화면으로 넘어갈 수 있습니다.
    * 이름과 연락처 영역을 long click 시 편집화면으로 넘어갑니다.
    * 프로필 사진 영역을 long click 시 사진 선택 화면으로 넘어갑니다.

  ***

  #### 기술 설명  

  * Recycler View를 이용하여 저장된 인물정보를 보여줍니다. Recycler View 는 adapter내의 데이터를 보여주는데, adapter는 arraylist 형식으로 itempanel에 넣을 이름 / 연락처 / 상세설명 / 프로필 사진 데이터를 담고 있습니다.
  * Shared Preference를 활용해서 연락처를 추가합니다. 사용자로부터 받은 데이터를 json file 형식으로 변환하고 해당 jsonObject array를 shared preference에 저장해줍니다. 이를 통해 앱이 꺼져도 그 전에 추가했던 연락처를 다시 볼 수 있게끔해줍니다.  
  * Image를 고르는 것과 관련해서는 Gallery Tab에서 자세히 설명할 예정입니다. Phonebook에서 연락처 데이터를 담는 adapter와 별개로 프로필 사진들만 따로 recyclerview 형식으로 보여주는 새로운 profileSelectAdapter를 만들었습니다.  
  * Phonebook / DetailedPhonebook / EditDetail / ProfileSelection 등 액티비티 간 데이터 교환은 intent를 통해 이뤄냈습니다.

  ***

  #### 주요기술: SharedPreference
  
  
  * LOAD:
  
      * shared preference를 우선 가져온다. key값을 첫 번째 인자로 넣고 특정 shared preference를 가져오면 된다.
      * 해당 sharedPreference 데이터를 활용하고 싶을 때, string으로 바꾸고 가져오면 된다. 여기서 또한 첫번째 인자는 key 값이고, 두번째 인자는 key값과 상응하는 value 값이 없을 때 가져오는 default 값이다. 
  
      ```java
      SharedPreferences sharedPreferences = getSharedPreferences("phonenumbers",MODE_PRIVATE);
      String savedPhonenumberData = sharedPreferences.getString("phonenumbers",null);
      ```
  
  
  * SAVE:
  
  
        * 특정 shared preference의 editor 객체를 생성하고, 해당 editor에 넣고 싶은 값을 넣으면 된다. 첫번째 인자는 key 값이고, 두번째 인자는 key값과 상응하는 value 값이다. 마지막으로 git에 올리듯 commit()을 해주면 된다.
      
      ```java
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(received_profile_number, received_drawable_number);
      editor.commit();
      ```



***

  ### TAB 2 - Gallery  

![gallery](https://user-images.githubusercontent.com/48674812/177324217-486e67f2-eb09-40f9-bfca-54e3f2357c98.png)

  #### Major features   

  * 두 개의 갤러리가 있습니다. 좌/우 측 하단 화살표로 다른 갤러리로 이동할 수 있습니다.
  * 갤러리에서 사진을 터치하면 큰 화면으로 볼 수 있습니다.
  * 첫 번째 갤러리에서는 drawable folder에 있는 엄선한 30개의 귀여운 캐릭터를 볼 수 있습니다.
  * 두 번째 갤러리에서는 핸드폰의 앨범과 연동해 사진을 가져올 수 있습니다. 또한 카메라 버튼을 눌러 사진을 찍고 즉시 갤러리로 가져올 수 있습니다.
  * 두 번째 갤러리 사진들을 longClick을 하면 삭제할 수 있습니다.

***

  #### 기술 설명  

  * Recycler View를 이용하여 세 개의 열로 drawable 내의 사진들을 보여줍니다. tab1 때와 마찬가지로 adapter와 viewholder를 사용하여 imagepanel에 png 파일들을 drawable 형식으로 나열하여 보여줍니다. 
  * layout 측면에서 사진들을 생동감있게 보여주기 위해서 image panel을 card view로 구현하였습니다. 
  * 첫 번째 gallery에 보여주는 사진의 형식은 drawable이고, 두번째 gallery에 보여주는 사진의 형식은 uri입니다.
  * 사진을 찍고 나서 받아오는 형식은 bitmap이라, gallery에 바로 띄워주기 위해서 형식을 uri로 바꿔주는 함수 getImageUri를 구현했습니다.

***

  #### 주요기술: onActivityResult


  * 두번째 갤러리 사진 리스트 구현: 


    * 전역 uri arrayList로 구현하였습니다. 특정 사진을 UriBigImage라는 새로운 activity로 확대하여 보여줄 때, 삭제할 때 등 편하게 indexing하여서 작업하기 위해서입니다. 
    
      ```java
      public static ArrayList<Uri> uriList = new ArrayList<>();
      ```


​      



  * 갤러리 연동:

    * 사진을 한 장 선택했을 때, 여러 장을 선정했을 때 나눠서 구현했습니다. 
    * data는 intent type으로, uri 이미지 등 가져올 수 있습니다. 사진이 여러 장일 경우 clipdata로 가져오는 데, 한 개 이상의 실제 데이터를 가져올 경우 clip data를 활용해서 가져와야 합니다.
    * 가져온 data를 uri 형식으로 바꿔서, uriList에 하나씩 넣어줍니다. 그 후 해당 uriList를 uriImageAdapter의 생성자에 넣어줘서, 어댑터에 현재까지 선택했던 모든 uri 이미지를 넣어줍니다.

    ```java
    if(requestCode == 0 && resultCode == RESULT_OK) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if(data != null){ 
            if (data.getClipData() == null) { 
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imageUri = data.getData();
                uriList.add(imageUri);
            } else {  
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));
    
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();  
                    try {
                        uriList.add(imageUri); 
                    } catch (Exception e) {
                        Log.e("MultiImageActivity", "File select error", e);
                    }
                }
            }
            adapter = new UriImageAdapter(uriList,getApplicationContext());
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            recyclerView.setAdapter(adapter);
        }
    }
    ```

  * 카메라 연동:


    * 카메라에서 받아온 데이터를 bundle로 가져옵니다. Bundle은 Map형태로 여러가지의 타입의 값을 저장하는 클래스입니다. key값과 value로 이뤄져 있습니다.
    * 이때 bundle에서 string 값 key "data"를 이용해서 bitmap의 value를 가져옵니다.
    * 그 후 같은 uriList에서 저장 될 수 있게 getImageUri 함수를 이용해서 bitmap -> uri로 변환해줍니다.
    
    ```java
    else if(requestCode == 2 && resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();
    
        Bitmap imageBitmap = (Bitmap) extras.get("data");
    
        Uri uri = getImageUri(getApplicationContext(), imageBitmap);
    
        uriList.add(uri);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new UriImageAdapter(uriList,getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
    
    }
    ```


​    


***

  ### TAB 3 - Namecard   

![namecard](https://user-images.githubusercontent.com/48674812/177324315-44e75a58-5d11-4643-926b-2cc7bbc64be3.png)

  #### Major features   

  * 연락처 또는 원하는 텍스트와 30개의 drawable 이미지를 이용해 포토카드를 만들 수 있습니다.
  * 만든 포토카드를 갤러리에 저장하거나 인스타 스토리로 공유할 수 있습니다.
  * 텍스트의 위치 / 크기 / 색상을 수정할 수 있습니다.

***

  #### 기술 설명  

  * Recycler View를 이용하여 세 개의 열로 drawable 내의 사진들을 보여줍니다. tab1 때와 마찬가지로 adapter와 viewholder를 사용하여 imagepanel에 png 파일들을 drawable 형식으로 나열하여 보여줍니다. 
  * layout 측면에서 사진들을 생동적으로 보여주기 위해서 image panel을 card view로 구현하였습니다. 
  * 첫 번째 gallery에 보여주는 사진의 형식은 drawable이고, 두번째 gallery에 보여주는 사진의 형식은 uri입니다.
  * 사진을 찍고 나서 받아오는 형식은 bitmap이라, gallery에 바로 띄워주기 위해서 형식을 uri로 바꿔주는 함수 getImageUri를 구현했습니다.


***

#### 주요기술: Intent (인텐트)




  * 명시적 인텐트 (Explicit Intent) - 텍스트 위치 / 크기 / 색상 전달:

    * 명확히 class를 지정해서 각 class간 서로 필요한 데이터를 전달합니다:

      * ImageSelection --> about:   image 전달

      * NameSelection --> about:   텍스트 전달

      * CombineImage --> Namecard: image / 텍스트 / 텍스트 위치, 크기, 색상 전달

    * combineImage에서 각 버튼을 눌렀을 때, setTextSize() , setAllCaps() , setTextColor(), setBackgroundColor() 등 함수를 통해서 각 이미지 / 텍스트 뷰 내 attributes를 변경합니다.

    * NamecardAdapter에서 전달 받은 모든 attribute를 각기 저장하는 arraylist를 구현해, 각 namecard마다의 attribute를 recycleview로 namecardpanel를 통해 출력합니다.

    ```java
    confirmAddButton = (Button) findViewById(R.id.confirm_add_button);
    confirmAddButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Namecard.class);
            Namecard.numofarray+=1;
            intent.putExtra("ImageNum", savedDrawableNumber);
            intent.putExtra("text",savedNameTextData +"\n" +savedPhonenumberTextData );
            intent.putExtra("TextSize", fontSize);
            intent.putExtra("TransX", combinedText.getTranslationX());
            intent.putExtra("TransY", combinedText.getTranslationY());
            intent.putExtra("Caps",setAllCaps);
            intent.putExtra("textColor",Integer.toString(textColor));
            intent.putExtra("bgColor",Integer.toString(bgColor));
            startActivity(intent);
        }
    });
    ```

    

  * 암시적 인텐트 (Implicit Intent) - 인스타그램 스토리 공유:

    * 명확히 class를 지정하지 않고 intent를 호출합니다. sourceApplication 값을 uri로 넣어주고, com.instagram.share.ADD_TO_STORY 액션을 처리할 수 있는 액티비티를 기기에 설치된 앱들 중 찾습니다. 
    * 해당 intent에 imagelayout에 있는 모든 view를 그대로 전송합니다. 화면 캡처하듯이 말입니다. 
    * setDrawingCacheEnabled() 함수로 cache에 해당 뷰의 이미지를 저장합니다.
    * getDrawingCache() 함수로 cache에 저장 되어 있는 Bitmap을 가져옵니다. tab2에서 사용했던 getImageUri () 함수로 bitmap에서 uri로 전환시킵니다.

    ```java
    imageLayout.setDrawingCacheEnabled(true);
    Bitmap bm = imageLayout.getDrawingCache();
    Uri bgUri = getImageUri(getApplicationContext(), bm);
    String sourceApplication = "com.khs.instagramshareexampleproject";
    
    // Instantiate implicit intent with ADD_TO_STORY action,
    // sticker asset, and background colors
    Intent intent = new Intent("com.instagram.share.ADD_TO_STORY");
    intent.putExtra("source_application", sourceApplication);
    intent.setType("image/png");
    intent.setDataAndType(bgUri, "image/png");
    
    // Instantiate activity and verify it will resolve implicit intent
    grantUriPermission(
        "com.instagram.android", null, Intent.FLAG_GRANT_READ_URI_PERMISSION
    );
    grantUriPermission(
        "com.instagram.android", bgUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
    );
    startActivity(intent);
    ```

    