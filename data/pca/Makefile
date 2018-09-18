INPUTS= simple basilevsy compressor_per_day_kwh compressor_1_day_detail

all:
	@for i in $(INPUTS) ; do \
		java -cp ../target/pca-1.0.jar com.uwemeding.pca.Main $$i ; \
	done
    

clean:; rm -f *lambda* *pcomps* *pfacs* *_cc* *_cumcon*

