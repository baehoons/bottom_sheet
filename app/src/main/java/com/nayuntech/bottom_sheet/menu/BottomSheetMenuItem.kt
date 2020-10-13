package com.nayuntech.bottom_sheet.menu

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.*
import androidx.annotation.DrawableRes

class BottomSheetMenuItem
/**
 * Creates a MenuItem
 *
 * @param context       Context of the MenuItem
 * @param group         Group id of the MenuItem
 * @param id            Id of the MenuItem
 * @param categoryOrder Category order of the MenuItem
 * @param ordering      Ordering of the MenuItem
 * @param title         Title of the MenuItem
 */
    (
    private val context: Context,
    private val group: Int,
    private val id: Int,
    private val categoryOrder: Int,
    private val ordering: Int,
    private var title: CharSequence?
) : MenuItem {

    private var titleCondensed: CharSequence? = null

    private var mIntent: Intent? = null

    private var shortcutNumericChar: Char = ' '

    private var shortcutAlphabeticChar: Char = ' '

    private var iconDrawable: Drawable? = null

    private var iconResId = NO_ICON

    private var clickListener: MenuItem.OnMenuItemClickListener? = null

    private var flags = ENABLED

    /**
     * Creates a MenuItem
     *
     * @param context Context of the MenuItem
     * @param title   Title of the MenuItem
     * @param icon    Drawable resource of the MenuItem
     */
    constructor(context: Context, title: CharSequence, @DrawableRes icon: Int) : this(
        context,
        0,
        0,
        0,
        0,
        title
    ) {
        setIcon(icon)
    }

    /**
     * Creates a MenuItem
     *
     * @param context Context of the MenuItem
     * @param title   Title of the MenuItem
     * @param icon    Drawable of the MenuItem
     */
    constructor(context: Context, title: CharSequence, icon: Drawable) : this(
        context,
        0,
        0,
        0,
        0,
        title
    ) {
        setIcon(icon)
    }

    /**
     * Creates a MenuItem
     *
     * @param context Context of the MenuItem
     * @param id      Id of the MenuItem
     * @param title   Title of the MenuItem
     * @param icon    Drawable resource of the MenuItem
     */
    constructor(context: Context, id: Int, title: CharSequence, @DrawableRes icon: Int) : this(
        context,
        0,
        id,
        0,
        0,
        title
    ) {
        setIcon(icon)
    }

    /**
     * Creates a MenuItem
     *
     * @param context Context of the MenuItem
     * @param id      Id of the MenuItem
     * @param title   Title of the MenuItem
     * @param icon    Drawable of the MenuItem
     */
    constructor(context: Context, id: Int, title: CharSequence, icon: Drawable) : this(
        context,
        0,
        id,
        0,
        0,
        title
    ) {
        setIcon(icon)
    }

    override fun getAlphabeticShortcut(): Char {
        return shortcutAlphabeticChar
    }

    override fun getGroupId(): Int {
        return group
    }

    override fun getIcon(): Drawable? {
        return iconDrawable
    }

    override fun getIntent(): Intent? {
        return mIntent
    }

    override fun getItemId(): Int {
        return id
    }

    override fun getMenuInfo(): ContextMenu.ContextMenuInfo? {
        return null
    }

    override fun getNumericShortcut(): Char {
        return shortcutNumericChar
    }

    override fun getOrder(): Int {
        return ordering
    }

    override fun getSubMenu(): SubMenu? {
        return null
    }

    override fun getTitle(): CharSequence? {
        return title
    }

    override fun getTitleCondensed(): CharSequence? {
        return if (titleCondensed != null) titleCondensed else title
    }

    override fun hasSubMenu(): Boolean {
        return false
    }

    override fun isCheckable(): Boolean {
        return flags and CHECKABLE != 0
    }

    override fun isChecked(): Boolean {
        return flags and CHECKED != 0
    }

    override fun isEnabled(): Boolean {
        return flags and ENABLED != 0
    }

    override fun isVisible(): Boolean {
        return flags and HIDDEN == 0
    }

    override fun setAlphabeticShortcut(alphaChar: Char): MenuItem {
        shortcutAlphabeticChar = alphaChar
        return this
    }

    override fun setCheckable(checkable: Boolean): MenuItem {
        flags = flags and CHECKABLE.inv() or if (checkable) CHECKABLE else 0
        return this
    }

    fun setExclusiveCheckable(exclusive: Boolean): BottomSheetMenuItem {
        flags = flags and EXCLUSIVE.inv() or if (exclusive) EXCLUSIVE else 0
        return this
    }

    override fun setChecked(checked: Boolean): MenuItem {
        flags = flags and CHECKED.inv() or if (checked) CHECKED else 0
        return this
    }

    override fun setEnabled(enabled: Boolean): MenuItem {
        flags = flags and ENABLED.inv() or if (enabled) ENABLED else 0
        return this
    }

    override fun setIcon(icon: Drawable): MenuItem {
        iconDrawable = icon
        iconResId = NO_ICON
        return this
    }

    override fun setIcon(iconRes: Int): MenuItem {
        if (iconRes != NO_ICON) {
            iconResId = iconRes
            iconDrawable = context.resources.getDrawable(iconRes)
        }

        return this
    }

    override fun setIntent(intent: Intent): MenuItem {
        mIntent = intent
        return this
    }

    override fun setNumericShortcut(numericChar: Char): MenuItem {
        shortcutNumericChar = numericChar
        return this
    }

    override fun setOnMenuItemClickListener(menuItemClickListener: MenuItem.OnMenuItemClickListener): MenuItem {
        clickListener = menuItemClickListener
        return this
    }

    override fun setShortcut(numericChar: Char, alphaChar: Char): MenuItem {
        shortcutNumericChar = numericChar
        shortcutAlphabeticChar = alphaChar
        return this
    }

    override fun setTitle(title: CharSequence): MenuItem {
        this.title = title
        return this
    }

    override fun setTitle(title: Int): MenuItem {
        this.title = context.resources.getString(title)
        return this
    }

    override fun setTitleCondensed(title: CharSequence?): MenuItem {
        titleCondensed = title
        return this
    }

    override fun setVisible(visible: Boolean): MenuItem {
        flags = flags and HIDDEN or if (visible) 0 else HIDDEN
        return this
    }

    operator fun invoke(): Boolean {
        if (clickListener != null && clickListener!!.onMenuItemClick(this)) {
            return true
        }

        if (mIntent != null) {
            context.startActivity(mIntent)
            return true
        }

        return false
    }

    override fun setShowAsAction(show: Int) {
        // Do nothing. ActionMenuItems always show as action buttons.
    }

    override fun setActionView(actionView: View): MenuItem {
        throw UnsupportedOperationException()
    }

    override fun getActionView(): View? {
        return null
    }

    override fun setActionView(resId: Int): MenuItem {
        throw UnsupportedOperationException()
    }

    override fun getActionProvider(): ActionProvider? {
        return null
    }

    override fun setActionProvider(actionProvider: ActionProvider): MenuItem {
        throw UnsupportedOperationException()
    }

    override fun setShowAsActionFlags(actionEnum: Int): MenuItem {
        setShowAsAction(actionEnum)
        return this
    }

    override fun expandActionView(): Boolean {
        return false
    }

    override fun collapseActionView(): Boolean {
        return false
    }

    override fun isActionViewExpanded(): Boolean {
        return false
    }

    override fun setOnActionExpandListener(listener: MenuItem.OnActionExpandListener): MenuItem {
        // No need to save the listener; ActionMenuItem does not support collapsing items.
        return this
    }

    companion object {

        private val NO_ICON = 0

        private val CHECKABLE = 0x00000001

        private val CHECKED = 0x00000002

        private val EXCLUSIVE = 0x00000004

        private val HIDDEN = 0x00000008

        private val ENABLED = 0x00000010
    }
}