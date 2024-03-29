/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.ui;

import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Button;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Chrome;
import com.watabou.pixeldungeon.scenes.PixelScene;

public class RedButton extends Button {

	protected NinePatch bg;
	protected BitmapText text;
	protected RenderedText rText;
	protected Image icon;

	public RedButton( String label ) {
		super();
		rText = PixelScene.renderText(9);
		add(rText);
 		rText.text(label);
	}

	@Override
	protected void createChildren() {
		super.createChildren();
		bg = Chrome.get( Chrome.Type.BUTTON );
		add( bg );

		text = PixelScene.createText( 9 );
	}

	@Override
	protected void layout() {
		super.layout();
		bg.x = x;
		bg.y = y;
		bg.size( width, height );

        rText.x = x + (width - rText.width()) / 2;
        rText.y = y + (height - rText.baseLine()) / 2;

		if (icon != null) {
			icon.x = x + rText.x - icon.width() - 2;
			icon.y = y + (height - icon.height()) / 2;
		}
	};

	@Override
	protected void onTouchDown() {
		bg.brightness( 1.2f );
		Sample.INSTANCE.play( Assets.SND_CLICK );
	};

	@Override
	protected void onTouchUp() {
		bg.resetColor();
	};

	public void enable( boolean value ) {
		active = value;
        rText.alpha(value ? 1.0f : 0.3f );
	}

	public void text( String value ) {
        rText.text(value);
		layout();
	}

	public void textColor( int value ) {
        rText.hardlight(value);
	}

	public void icon( Image icon ) {
		if (this.icon != null) {
			remove( this.icon );
		}
		this.icon = icon;
		if (this.icon != null) {
			add( this.icon );
			layout();
		}
	}

	public float reqWidth() {
        return rText.width+4;
	}

	public float reqHeight() {
        return rText.baseLine()+4;
	}
}
