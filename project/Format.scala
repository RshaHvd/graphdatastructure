import scalariform.formatter.preferences.IFormattingPreferences

object Format {

  val formattingPreferences: IFormattingPreferences = {
    import scalariform.formatter.preferences._

    FormattingPreferences().
      setPreference(AlignParameters, true).
     // setPreference(DoubleIndentClassDeclaration, false).
      setPreference(DoubleIndentConstructorArguments, false).
      setPreference(DoubleIndentMethodDeclaration, false).
      setPreference(AlignParameters, true).
      setPreference(AlignParameters, true).
      setPreference(AlignSingleLineCaseStatements, true).
      setPreference(CompactControlReadability, true).
      setPreference(CompactStringConcatenation, false).
      setPreference(DanglingCloseParenthesis, Preserve).
      setPreference(FormatXml, true).
      setPreference(IndentLocalDefs, true).
      setPreference(IndentPackageBlocks, true).
      setPreference(IndentSpaces, 2).
      setPreference(MultilineScaladocCommentsStartOnFirstLine, false).
      setPreference(PreserveSpaceBeforeArguments, false).
      setPreference(RewriteArrowSymbols, false).
      setPreference(SpaceBeforeColon, false).
      setPreference(SpaceInsideBrackets, false).
      setPreference(SpaceInsideParentheses, false).
      setPreference(SpacesWithinPatternBinders, true)
  }
}