
pipe = function(fileName, numSNPS){
	command = paste(paste("java PSim ", fileName, sep = ""), ".txt ", numSNPS, " result", sep = "")
	system(command)	
	
	#run through plink
	system(paste("./plink --file result --noweb --compound-genotypes --make-bed --out", fileName))

	
}
