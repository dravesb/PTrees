args = commandArgs(T)

#Get relevent directories
parentdir = getwd()
outdir = paste(paste(parentdir, "/", sep = ""),args[1],sep="")
PGen_dir = paste(parentdir,"/PGen", sep="")
PSim_dir = paste(parentdir,"/PSim", sep ="")


#Movefam file from outdir to PSim
file = paste(paste(outdir, "/", sep =""),paste(args[1],".txt", sep=""), sep="")
system(paste(paste("cp ", file, sep = ""),PSim_dir,sep=" "))

#Simulate 
setwd(PSim_dir)
source("pipe.r")
pipe(args[1], args[4])

#Move results to outdir
system(paste("mv ", args[1],".bim ", outdir, sep = ""))
system(paste("mv ", args[1],".fam ", outdir, sep = ""))
system(paste("mv ", args[1],".bed ", outdir, sep = ""))

#Delete uncessary files
system(paste("rm ", args[1], ".txt", sep = ""))
system(paste("rm ", args[1], ".log", sep = ""))
system("rm result.ped")
system("rm result.map")
setwd("..")


