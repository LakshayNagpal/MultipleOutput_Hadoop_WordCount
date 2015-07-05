//WordCount Reducer - Multiple Output Variant

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;


public class MultipleOutputReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private MultipleOutputs<Text, IntWritable>  multipleoutputs;
	
@Override
public void setup(Context context) throws IOException, InterruptedException{
	multipleoutputs = new MultipleOutputs<Text, IntWritable>(context);
	
}

@Override
public void reduce(Text key, Iterable<IntWritable>values, Context context)
throws IOException, InterruptedException {
	
	int sum = 0;
	for(IntWritable value:values){
		sum += value.get();
		}
		multipleoutputs.write(key, new IntWritable(sum), key.toString().substring(0, 1));
	}

@Override
public void cleanup(Context context)
throws IOException, InterruptedException{
	
	multipleoutputs.close();
	}
}
