package gcar.Entidades;

import gcar.Entidades.util.JsfUtil;
import gcar.Entidades.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("locacaoController")
@SessionScoped
public class LocacaoController implements Serializable {

    private Locacao current;
    private DataModel items = null;
    @EJB
    private gcar.Entidades.LocacaoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public LocacaoController() {
    }

    public Locacao getSelected() {
        if (current == null) {
            current = new Locacao();
            current.setLocacaoPK(new gcar.Entidades.LocacaoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private LocacaoFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Locacao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Locacao();
        current.setLocacaoPK(new gcar.Entidades.LocacaoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getLocacaoPK().setLivroidLivro(current.getLivro().getIdLivro());
            current.getLocacaoPK().setAlunoidAluno(current.getAluno().getIdAluno());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocacaoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Locacao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getLocacaoPK().setLivroidLivro(current.getLivro().getIdLivro());
            current.getLocacaoPK().setAlunoidAluno(current.getAluno().getIdAluno());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocacaoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Locacao) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("LocacaoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Locacao getLocacao(gcar.Entidades.LocacaoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Locacao.class)
    public static class LocacaoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LocacaoController controller = (LocacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "locacaoController");
            return controller.getLocacao(getKey(value));
        }

        gcar.Entidades.LocacaoPK getKey(String value) {
            gcar.Entidades.LocacaoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new gcar.Entidades.LocacaoPK();
            key.setIdLocacao(Integer.parseInt(values[0]));
            key.setAlunoidAluno(Integer.parseInt(values[1]));
            key.setLivroidLivro(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(gcar.Entidades.LocacaoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdLocacao());
            sb.append(SEPARATOR);
            sb.append(value.getAlunoidAluno());
            sb.append(SEPARATOR);
            sb.append(value.getLivroidLivro());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Locacao) {
                Locacao o = (Locacao) object;
                return getStringKey(o.getLocacaoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Locacao.class.getName());
            }
        }

    }

}
