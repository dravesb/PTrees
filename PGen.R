args = commandArgs(T)

#Get relevent directories
parentdir = getwd()
outdir = paste(paste(parentdir, "/", sep = ""),args[1],sep="")
PGen_dir = paste(parentdir,"/PGen", sep="")
PSim_dir = paste(parentdir,"/PSim", sep ="")

#Make out dir
system(paste("rm -r ", outdir, sep = ""))
system(paste("mkdir",outdir))

#Generate Pedigree
setwd(PGen_dir)
source("PGen_Viz.r")
createPed(args[1],as.numeric(args[2]),as.numeric(args[3]),as.numeric(args[4]),as.numeric(args[5]),as.numeric(args[6]),as.numeric(args[7]))

#Move results to outdir

file = paste(args[1],".txt", sep ="")
system(paste("mv myPed.ps ", outdir, sep = ""))
system(paste(paste("mv ", file, sep = ""),outdir,sep=" "))

#Remove unnecessary files
system("rm myPed.topology.txt")
system(paste(paste("rm ", args[1], sep = ""), ".viz.txt", sep =""))

#Reset Directory 
setwd("..")