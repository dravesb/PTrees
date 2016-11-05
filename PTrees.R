args = commandArgs(T)

text = read.table(paste(args[1], ".txt", sep = ""), stringsAsFactor = F)


fileName = text[which(text[,1]=="outfile"),3]
families = as.numeric(text[which(text[,1]=="families"),3])
generations = as.numeric(text[which(text[,1]=="generations"),3])
prob_reprod = as.numeric(text[which(text[,1]=="prob_reprod"),3])
min_offspring = as.numeric(text[which(text[,1]=="min_offspring"),3])
max_offspring = as.numeric(text[which(text[,1]=="max_offspring"),3])
randoms = as.numeric(text[which(text[,1]=="randoms"),3])
snps = as.numeric(text[which(text[,1]=="snps"),3])

if(families<1 || families%%1 != 0){
	message("Must have an integer value greater than 1 number of families. Exiting PGEN.")
	stop()
}
if(generations<1 || generations%%1 != 0){
	message("Must have an integer value greater than 1 number of generations. Exiting PGEN.")
	stop()
}
if(0>=prob_reprod || prob_reprod>=1){
	message("Must have a probability of reproduction value greater than between 0 and 1 (exclusive). Exiting PGEN.")
	stop()
}
if(min_offspring<1 || min_offspring%%1 != 0 || min_offspring>max_offspring){
	message("Must have an integer value greater than 0 number of min_offspring. Exiting PGEN.")
	stop()
}
if(max_offspring>10 || max_offspring%%1 != 0){
	message("Must have an integer value less than 10 number of max_offspring. Exiting PGEN.")
	stop()
}
if(randoms>10000 || randoms%%1 != 0){
	message("Must have an integer value less than 10000 number of unrelated individuals. Exiting PGEN.")
	stop()
}
if(snps<1000 || snps>100000 || snps%%1 != 0){
	message("Number of snps cannot exceed 100000 and cannot be less than 1000. Exiting PGEN.")
	stop()
}

#---------------------------------------------------------------------------------------


#PGen
parameters = as.character(c(fileName, families, generations, prob_reprod, min_offspring, max_offspring, randoms))
system(paste("RScript PGen.R", paste(parameters, collapse = " ")))


#PSim
parameters = as.character(c(fileName, snps))
system(paste("RScript PSim.R", paste(parameters, collapse = " ")))









