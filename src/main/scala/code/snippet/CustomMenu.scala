package code.snippet

import net.liftweb.sitemap.FlexMenuBuilder
import net.liftweb.http.{S, DispatchSnippet}
import xml.{Elem, NodeSeq}

/**
 * Created with IntelliJ IDEA.
 * User: christian
 * Date: 12/13/12
 * Time: 7:56 PM
 * To change this template use File | Settings | File Templates.
 */
object CustomMenu extends FlexMenuBuilder with DispatchSnippet {


	def dispatch: DispatchIt = overridenDispatch orElse net.liftweb.builtin.snippet.Menu.dispatch

	def overridenDispatch: DispatchIt = {
		case "builder" => ignore => render

	}

	override def expandAll  = false
	override def linkToSelf = true


	override def updateForPath(nodes: Elem, path: Boolean): Elem = {
		if (path){
			nodes % S.mapToAttrs(Map("class" -> "active"))
		} else{
			nodes
		}
	}

	override def buildInnerTag(contents: NodeSeq, path: Boolean, current: Boolean): Elem ={
		updateForCurrent(updateForPath(<dd>{contents}</dd>, path), current)
	}

	override def updateForCurrent(nodes: Elem, current: Boolean): Elem = {
		if (current){
			nodes % S.mapToAttrs(Map("class" -> "active"))
		} else{
			nodes
		}
	}

	override def renderOuterTag(inner: NodeSeq, top: Boolean): NodeSeq ={
//		if (top){
			<dl class="tabs">{inner}</dl>
//		}else{
//			<div class="subnav subnav-fixed">
//				<ul class="nav nav-pills">{inner}</ul>
//			</div>
//		}
	}

	override def emptyPlaceholder: NodeSeq = {
		println("=======")
		<li class="vertical-divider"></li>
	}

}
