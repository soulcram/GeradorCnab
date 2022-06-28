package br.com.m3Tech.solucoesFromtis.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.google.common.collect.Lists;

import br.com.m3Tech.exception.DataInvalidaException;
import br.com.m3Tech.exception.NumeroInvalidoException;

public class FileUtil {
	
	private static final int BUFFER = 2048;

	private FileUtil() {
	}
	
	static List<File> retorno = Lists.newArrayList();

	public static List<File> getFiles(String caminho, String filtro) {

		retorno = Lists.newArrayList();

		addFiles(caminho, filtro);
		
		return retorno;
	}
	
	public static void limparDiretorio(String caminho) {

		File diretorio = new File(caminho);
		
		if(diretorio.isDirectory()) {
		
			for (File f : diretorio.listFiles()) {
				f.delete();
			}
		}
	}
	
	public static List<File> getDiferencaNoSource(List<File> destino, List<File> source) {

		retorno = Lists.newArrayList();

		for(File fSource : source) {
			
			boolean encontrou = false;
			
			for(File fDestino : destino) {
				if(fDestino.getName().equals(fSource.getName())) {
					encontrou = true;
					break;
				}
			}
			
			if(!encontrou) {
				retorno.add(fSource);
			}
			
		}
		
		return retorno;
	}
	
	
	private static void addFiles(String caminho, String filtro) {
		
		File diretorio = new File(caminho);
			
		

		for (File f : diretorio.listFiles()) {

			if (f.isFile()) {
				
				if (aceitarFile(f.getName(), filtro)) {
					retorno.add(f);
				}
				
			} else if (f.isDirectory()) {
				if (!ignorarDiretorio(f.getName())) {
					addFiles(f.getPath(), filtro);
				}

			}

		}
		
	}

	private static boolean aceitarFile(String name, String filtro) {
		
		List<String> arquivosParaIgnorar = Lists.newArrayList("project", "classpath", "xlsx", "svn","cmd");
		try {
			if(arquivosParaIgnorar.contains(name.split("\\.")[1])) {
				return false;
			}
		}catch(Exception e) {
			return false;
		}
		
		if("Todos".equalsIgnoreCase(filtro)) {
			return true;
		}
		
		return name.contains(filtro) ;
	}

	private static boolean ignorarDiretorio(String name) {
		List<String> pastaParaIgnorar = Lists.newArrayList("target", ".settings", "dist", ".svn");

		return pastaParaIgnorar.contains(name);
	}
	
	public static byte[] zipBytes(String filename, byte[] input) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		ZipEntry entry = new ZipEntry(filename);
		entry.setSize(input.length);
		zos.putNextEntry(entry);
		zos.write(input);
		zos.closeEntry();
		zos.close();
		return baos.toByteArray();
	}

	public static void unzip(byte[] content, String pastaDestino) {

		try {

			File file = new File(pastaDestino);

			if (file.exists() == false) {
				file.mkdirs();
			}

			BufferedOutputStream dest = null;

			ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(content));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extraindo o arquivo: " + entry);
				int count;
				byte data[] = new byte[BUFFER];

				FileOutputStream fos = new FileOutputStream(pastaDestino + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unzip(String caminhoArquivo, String pastaDestino) {

		try {

			File file = new File(pastaDestino);

			if (file.exists() == false) {
				file.mkdirs();
			}

			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(caminhoArquivo);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {

				int count;
				byte data[] = new byte[BUFFER];

				FileOutputStream fos = new FileOutputStream(pastaDestino + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = zis.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			zis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteTempFiles(String caminho) {

		File pasta = new File(caminho);

		File[] listFiles = pasta.listFiles();

		for (File f : listFiles) {
			f.delete();
		}
	}

	public static void copyProperties(Object source, Object target) {

		Field[] fieldsTarget = null;
		try {
			fieldsTarget = target.getClass().getDeclaredFields();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Field[] fieldsSource = source.getClass().getDeclaredFields();

		for (Field fSource : fieldsSource) {

			for (Field fTarget : fieldsTarget) {

				if (fSource.getName().equals(fTarget.getName())) {

					fTarget.setAccessible(true);
					fSource.setAccessible(true);

					if (isBasic(fSource.getType())) {

						Object value = null;
						try {
							value = fSource.get(source);

						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							setValorDoField(fTarget, String.valueOf(value), target);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (isCollection(fSource.getType())) {

						try {
							@SuppressWarnings("unchecked")
							List<Object> listSource = (List<Object>) fSource.get(source);

							Method[] declaredMethods = target.getClass().getDeclaredMethods();

							String nomeMetodo = "set" + fTarget.getName();
							String nomeAtributo = "";

							Class<?> forName = Class.forName(fTarget.getType().getTypeName());

							Field[] declaredFields = forName.getDeclaredFields();

							Object targetDaList = null;

							Class<?> newForName = null;

							for (Field f : declaredFields) {
								f.setAccessible(true);
								Type type = f.getGenericType();

								if (type instanceof ParameterizedType) {
									ParameterizedType pt = (ParameterizedType) type;
									for (Type t : pt.getActualTypeArguments()) {

										newForName = Class.forName(t.getTypeName());

										nomeAtributo = newForName.getSimpleName();

										targetDaList = newForName.newInstance();

									}
								}
							}

							List<Object> listTarget = new ArrayList<Object>();

							for (Object o : listSource) {

								Object newInstance = targetDaList.getClass().newInstance();
								copyProperties(o, newInstance);
								listTarget.add(newInstance);
							}

							for (Method c : declaredMethods) {
								if (c.getName().equalsIgnoreCase(nomeMetodo)) {

									Class<?>[] paramTypes = c.getParameterTypes();

									Object newInstance = paramTypes[0].newInstance();

									String nomeMetodo2 = "";

									Field[] declaredFields2 = newInstance.getClass().getDeclaredFields();

									for (Field f2 : declaredFields2) {
										f2.setAccessible(true);
										Type type = f2.getGenericType();

										if (type instanceof ParameterizedType) {
											ParameterizedType pt = (ParameterizedType) type;
											for (Type t : pt.getActualTypeArguments()) {

												newForName = Class.forName(t.getTypeName());

												String nomeAtributo2 = newForName.getSimpleName();

												nomeMetodo2 = "set" + nomeAtributo2;

												if (nomeAtributo2.equalsIgnoreCase(nomeAtributo)) {

													Method[] declaredMethods2 = newInstance.getClass()
															.getDeclaredMethods();

													for (Method m : declaredMethods2) {
														m.setAccessible(true);
														if (m.getName().equalsIgnoreCase(nomeMetodo2)) {

															m.invoke(newInstance, listTarget);

														}
													}
												}

											}
										}

									}

									c.invoke(target, newInstance);

								}
							}

						} catch (Exception e) {
							System.out.println(e);
						}
					} else {
						try {

							Object newInstanceFTarget = fTarget.getType().newInstance();

							Field declaredField = target.getClass().getDeclaredField(fTarget.getName());
							declaredField.setAccessible(true);

							Method[] declaredMethods = target.getClass().getDeclaredMethods();

							String nomeMetodo = "set" + fTarget.getName();

							copyProperties(fSource.get(source), newInstanceFTarget);

							for (Method c : declaredMethods) {
								if (c.getName().equalsIgnoreCase(nomeMetodo)) {
									c.invoke(target, newInstanceFTarget);
								}
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					break;

				}
			}

		}

	}

	private static boolean isCollection(Class<?> type) {

		Class<?>[] interfaces = type.getInterfaces();

		if (interfaces.length > 0) {
			if ("Collection".equals(interfaces[0].getSimpleName())) {
				return true;
			}
		}

		return false;
	}

	private static boolean isBasic(Class<?> type) {

		if (Integer.class == type) {
			return true;
		} else if (String.class == type) {
			return true;
		} else if (BigDecimal.class == type) {
			return true;
		} else if (Boolean.class == type) {
			return true;
		} else if (LocalDate.class == type) {
			return true;
		} else if (type.isEnum()) {
			return true;
		} else if (type.isPrimitive()) {
			return true;
		}else if (Date.class == type) {
			return true;
		}

		return false;
	}

	private static void setValorDoField(Field fieldTarget, String valueFieldSource, Object instancia) throws Exception {

		fieldTarget.setAccessible(true);

		Class<?> type = fieldTarget.getType();
		
		if (Integer.class == type|| type.getName().equalsIgnoreCase("int")) {
			fieldTarget.set(instancia, getInteger(valueFieldSource));
		} else if (String.class == type) {
			fieldTarget.set(instancia, getString(valueFieldSource));
		} else if (BigDecimal.class == type) {
			fieldTarget.set(instancia, getBigDecimal(valueFieldSource));
		} else if (Boolean.class == type || type.getName().equalsIgnoreCase("boolean")) {
			fieldTarget.set(instancia, getBoolean(valueFieldSource));
		} else if (LocalDate.class == type) {
			fieldTarget.set(instancia, getLocalDate(valueFieldSource));
		} else if (XMLGregorianCalendar.class == type) {
			fieldTarget.set(instancia, getXMLGregorianCalendar(valueFieldSource));
			
		} else if (type.isEnum()) {

			if (valueFieldSource != null && !"null".equalsIgnoreCase(valueFieldSource)) {
				Method[] declaredMethods = type.getDeclaredMethods();

				for (Method m : declaredMethods) {

					if ("valueOf".equals(m.getName())) {
						fieldTarget.set(instancia, m.invoke(m, valueFieldSource));
					}

				}

			}

		}

	}

	private static BigDecimal getBigDecimal(String s) throws NumeroInvalidoException {
		BigDecimal valor = null;

		if (!isEmpty(s)) {

			if (containsOnly(s, "0123456789.,")) {
				valor = new BigDecimal(s.replaceAll(",", "."));
			} else {
				valor = null;
			}

		}
		return valor;

	}

	private static Integer getInteger(String s) throws NumeroInvalidoException {
		Integer valor = null;
		if (!isEmpty(s)) {
			if (isNumeric(s)) {
				valor = Integer.valueOf(s);
			} else {
				valor = null;
			}

		}
		return valor;
	}

	public static boolean isNumeric(String s) {
		return containsOnly(s, "0123456789");
	}

	public static String getString(String s) {
		return s != null ? s : null;
	}

	private static XMLGregorianCalendar getXMLGregorianCalendar(String s) throws DataInvalidaException {

		XMLGregorianCalendar valor = null;
		if (!isEmpty(s)) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				
			Date date = format.parse(s);

			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(date);

				valor =  DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
		return valor;

	}
	
	private static LocalDate getLocalDate(String s) throws DataInvalidaException {

		LocalDate valor = null;
		if (!isEmpty(s)) {

			if (containsOnly(s, "0123456789-") && isData(s)) {
				valor = LocalDate.parse(s);
			} else {
				valor = null;
			}

		}
		return valor;

	}


	private static boolean isData(String data) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			sdf.parse(data);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	private static Boolean getBoolean(String s) throws NumeroInvalidoException {

		Boolean valor = false;
		if (!isEmpty(s)) {

			if ("true".equals(s) || "1".equals(s)) {
				valor = true;
			}

		}
		return valor;

	}

	private static boolean isEmpty(String valor) {
		if (isNull(valor)) {
			return true;
		}

		if (isBlank(valor)) {
			return true;
		}

		return false;
	}

	private static boolean isNull(String valor) {
		if (valor == null) {
			return true;
		}

		return false;
	}

	private static boolean isBlank(String valor) {
		if ("".equals(valor.trim())) {
			return true;
		}

		return false;
	}

	public static boolean containsOnly(String s1, String s2) {

		for (char c : s1.toCharArray()) {
			String s = String.valueOf(c);
			if (!s2.contains(s)) {
				return false;
			}
		}

		return true;
	}

}
