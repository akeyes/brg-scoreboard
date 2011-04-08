package com.carolinarollergirls.scoreboard.file;

import java.io.*;
import java.util.*;
import java.text.*;
import java.nio.charset.*;

import org.w3c.dom.*;

import com.carolinarollergirls.scoreboard.*;
import com.carolinarollergirls.scoreboard.model.*;
import com.carolinarollergirls.scoreboard.xml.*;

public class TeamFromXmlFile extends ScoreBoardFromXmlFile
{
	public TeamFromXmlFile(ScoreBoardModel sbm, String filename, String t) throws IOException {
		super(sbm, filename);
		setRealtime(false);

		teamId = t;
	}

	protected void processDocument(ScoreBoardModel model, Document document) {
		Element sb = editor.getElement(document.getDocumentElement(), "ScoreBoard");
		editor.getElement(sb, "Team").setAttribute("Id", teamId);
		TeamModel teamModel = model.getTeamModel(teamId);
		Iterator<SkaterModel> skaters = teamModel.getSkaterModels().iterator();
		while (skaters.hasNext()) {
			try {
				teamModel.removeSkaterModel(skaters.next().getId());
			} catch ( SkaterNotFoundException snfE ) {
			}
		}

		super.processDocument( model, document );
	}

	protected String teamId;
}
