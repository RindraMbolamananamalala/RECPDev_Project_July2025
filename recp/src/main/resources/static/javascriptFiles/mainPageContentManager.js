/**
 * 
 * The Javascript file in charge of the (Minor) Visual Management of the Main Page of the RECP tool
 */

console.log("JS codes successfully integrated!!!!");


function changePatternToSearchInformationNeededPlaceholderText(){
	/** 
	 * Everytime that the User's choice on the Type of Research to be done on Patterns changes,
	 * The Placeholder Text of the Search Area should inform him/her of the corresponding information
	 * needed  
	 * **/
	const typeOfResearch = document.getElementById("patternResearchType").value;
	if (typeOfResearch==="patternResearchByName"){
		// SEARCH by Pattern's name
		document.getElementById("InputPatternInformation").placeholder = "Pattern's name";
	}else if(typeOfResearch==="patternResearchByProblemToSolve"){
		// SEARCH by Pattern's Problem to solve 
		document.getElementById("InputPatternInformation").placeholder = "Pattern's problem to solve";
	} else {
		// Choice unclear... 
		document.getElementById("InputPatternInformation").placeholder = "Pattern";
	}
	console.log("A new choice of Type of Patterns'research was chosen by the user : " + typeOfResearch);
}
