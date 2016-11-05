createPed = function(fileName, numFam, numGens, prob_reprod, min_offspring, max_offpsring, random){
	
	#Generate pedigree
	args = paste(fileName, numFam, numGens, prob_reprod, min_offspring, max_offpsring, random)
	system(paste("java PGen", paste(args, collapse = " ")))
	
	
	#Format Visualation tool, Cranefoot
	tbl = read.table(paste(fileName, ".txt", sep =""), header = F)
	colors = ifelse(tbl[,5]==1,990000,formatC(99, width = 6, format ="d",flag="0"))
	labels = paste(paste(tbl[,1]," - ", sep =""),tbl[,2],sep = "")
	tbl = cbind(tbl,colors)	
	
	#Reassign names 
	father = numeric(length(tbl[,1]))
	mother = numeric(length(tbl[,1]))
	for(i in 1:length(tbl[,1])){
		
		dad = intersect(which(tbl[i,3]==tbl[,2]),which(tbl[,1]==tbl[i,1]))
		mom = intersect(which(tbl[i,4]==tbl[,2]),which(tbl[,1]==tbl[i,1]))
		
		ifelse(is.na(dad), father[i] <- 0, father[i] <- labels[dad])
		ifelse(is.na(mom), mother[i] <- 0, mother[i] <- labels[mom])
	}
	
	tbl[,2]=labels	
	tbl[,3]= father
	tbl[,4]=mother

	colnames(tbl) = c("myPed","Name","Father","Mother","Gender", "Color")
	
	#Write out file
	newFile = paste(fileName,".viz.txt", sep ="")
	write.table(tbl, newFile, quote = F, sep = "\t", row.names = F)
	
	#Run visualization
	system("./cranefoot config.txt")
}
