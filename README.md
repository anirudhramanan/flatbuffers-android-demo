##FlatBuffers vs JSON

I ran the demo app which contains a sample JSON file (963 bytes), and tried parsing the file using gson and flatbuffer.

Here is the screenshot of the demo app.

![Comparison](https://github.com/anirudhramanan/flatbuffers-android-demo/blob/master/comparison.png)

###Results

* Time : Flatbuffer took 3-8 ms, whereas gson took 30-40 ms to parse the file.
* Memory : Memory footprint was very less in case of FlatBuffers as compared to gson

##FlatBuffer Setup Intro

* Clone the flatbuffer github repo to your local machine
  ````git clone https://github.com/google/flatbuffers.git````
  
* ````cd flatbuffers````

* Run the command
  1) ````cmake -G "Unix Makefiles"````
  2) ````make````
  
* This will generate the flatc compiler in your root project directory.

* ````./flatc -j -b schema.fbs sample.json```` to generate the FlatBuff files.
